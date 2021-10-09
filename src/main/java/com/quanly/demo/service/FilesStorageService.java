package com.quanly.demo.service;

import com.quanly.demo.model.FileDb;
import com.quanly.demo.model.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FilesStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDb store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDb FileDb = new FileDb(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDb);
    }

    public FileDb getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDb> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

}
