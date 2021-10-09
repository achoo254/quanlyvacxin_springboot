package com.quanly.demo.model.repository;

import com.quanly.demo.model.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDb, String> {

}
