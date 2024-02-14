package com.sivellexfc.service;

import com.sivellexfc.client.AccountServiceClient;
import com.sivellexfc.client.StoreServiceClient;
import com.sivellexfc.dto.RequestProductDto;
import com.sivellexfc.dto.ResponseProductDto;
import com.sivellexfc.entity.Product;
import com.sivellexfc.repository.ProductRepository;
import com.sivellexfc.wrapper.ProductWrapper;
import jakarta.ws.rs.NotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final AccountServiceClient accountServiceClient;
    private final StoreServiceClient storeServiceClient;

    public ProductService(ProductRepository productRepository,
                          AccountServiceClient accountServiceClient,
                          StoreServiceClient storeServiceClient) {
        this.productRepository = productRepository;
        this.accountServiceClient = accountServiceClient;
        this.storeServiceClient = storeServiceClient;
    }

    @Transactional
    public ResponseEntity<Product> createProduct(RequestProductDto requestProductDto, String accountId) {
        requestProductDto.setSellerId(accountId);
        Product product = ProductWrapper.toEntity(requestProductDto);
        String productId = productRepository.save(product).getId();
        storeServiceClient.addProduct(productId,accountServiceClient.getStoreIdByAccountId(accountId));
        return ResponseEntity.ok(productRepository.save(product));
    }


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getProductsBySellerId(long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    public Optional<Product> getProductByProductId(String productId) {
        return productRepository.findById(productId);
    }

    public void deleteProductByProductId(long productId) {
        productRepository.deleteById(productId);
    }

    public ResponseEntity<ResponseProductDto> updateProduct(RequestProductDto requestProductDto, String accountId, String productId) throws AccountNotFoundException {
        try{
            if(accountServiceClient.isAccountExist(accountId) && accountServiceClient.isAccountSeller(accountId)){
                if(productRepository.findById(productId).isPresent() && productRepository.findById(productId).get().getSellerId().equals(accountId)){
                    Product updatedProduct = ProductWrapper.toEntity(requestProductDto,productId);
                    return ResponseEntity.ok(ProductWrapper.toResponseProductDto(productRepository.save(updatedProduct)));
                }
                else throw new NotAllowedException(null);
            }
            else throw new AccountNotFoundException();
        }catch (NotAllowedException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        } catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity deleteAll() {
        productRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }
}
