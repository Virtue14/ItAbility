package com.team3.itability.feed.dao;


import com.team3.itability.feed.dto.ImgDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepo extends JpaRepository<ImgDTO,Integer> {
}
