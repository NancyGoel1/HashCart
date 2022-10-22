package com.example.hashcartapp.repository;


import com.example.hashcartapp.entities.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

   /* @Query(value = "select * from advertisement GROUP BY category order by creation_date DESC", nativeQuery = true)
    public Advertisement findAdvertisementByCategory(@Param("category") String category);

    @Query(value = "select * from advertisement GROUP BY type order by creation_date DESC", nativeQuery = true)
    public Advertisement findAdvertisementByType(@Param("type") String type);

    @Query(value = "select * from advertisement GROUP BY location order by creation_date DESC", nativeQuery = true)
    public Advertisement findAdvertisementByLocation(@Param("location") String location);
*/
   @Query(value = "select * from advertisement a where a.category= :category ORDER BY a.creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByCategoryOrderByDateDesc(String category,Pageable page);

   @Query(value = "select * from advertisement a where a.type= :type ORDER BY a.creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByTypeOrderByDateDesc(String type,Pageable page);

   @Query(value = "select * from advertisement a where a.location= :location ORDER BY a.creation_date DESC", nativeQuery = true)
   Page<Advertisement> findAdvertisementByLocationOrderByDateDesc(String location,Pageable page);



}
