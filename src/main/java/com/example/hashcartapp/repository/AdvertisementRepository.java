package com.example.hashcartapp.repository;


import com.example.hashcartapp.entities.Advertisement;
import com.example.hashcartapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

//AdvertisementRepositoryCustom
   /**
    *
    * @param page
    * @return
    */
    /* static Specification<Advertisement> findAdvertisementByCategoryOrderByDateDesc(String category) {
      return (advertisementRoot, cq, cb) -> cb.equal(advertisementRoot.get("category"), category);
   }*/
  @Query(value = "select * from advertisement where category =:category order by creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByCategoryOrderByDateDesc(@Param("category") String category,Pageable page);

   @Query(value = "select * from advertisement GROUP BY type order by creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByTypeOrderByDateDesc(Pageable page);

   @Query(value = "select * from advertisement GROUP BY location order by creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByLocationOrderByDateDesc(Pageable page);

}
