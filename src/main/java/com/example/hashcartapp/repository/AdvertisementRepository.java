package com.example.hashcartapp.repository;


import com.example.hashcartapp.entities.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

   @Query(value = "select * from advertisement  where category= :category ORDER BY creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByCategoryOrderByDateDesc(String category, Pageable page);

   @Query(value = "select * from advertisement where type= :type ORDER BY creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByTypeOrderByDateDesc(String type,Pageable page);

   @Query(value = "select * from advertisement  where location= :location ORDER BY creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByLocationOrderByDateDesc(String location,Pageable page);

  /* @Query(value = "UPDATE advertisement SET is_advertisement_deleted= 'true' where advertisement_id= :advertisementId", nativeQuery = true)
   Long deleteAd(Long advertisementId);*/
}
