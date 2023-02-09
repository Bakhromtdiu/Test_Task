package com.example.task.service;

import com.example.task.dtos.request.AttachDTO;
import com.example.task.entity.attach.Attach;
import com.example.task.exception.NotFoundException;
import com.example.task.repository.AttachRepository;
import com.sun.tools.attach.AttachOperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachService {
    @Value("${attach.upload.folder}")
    public String attachFolder;
    @Value("${attach.open.url}")
    public String attachOpenUrl;
    @Autowired
    AttachRepository attachRepository;

    public AttachDTO saveToSystem(MultipartFile file) {
        try {
            // attaches/2022/04/23/UUID.png
            String attachPath = getYmDString(); // 2023/02/07/
            String extension = getExtension(file.getOriginalFilename()); // .png
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "." + extension; // UUID.png

            File folder = new File(attachFolder + attachPath);  // attaches/2023/02/07/
            if (!folder.exists()) {
                folder.mkdirs();
            }

            byte[] bytes = file.getBytes();

            Path path = Paths.get(attachFolder + attachPath + "/" + fileName); // attaches/2023/02/07/UUID.png
            Files.write(path, bytes);

            Attach entity = new Attach();
            entity.setPath(attachPath);
            entity.setExtension(extension);
            entity.setSize(file.getSize());
            entity.setOriginalName(file.getOriginalFilename());
            entity.setCreatedData(LocalDateTime.now());
            entity.setAttachOpenUrl(attachOpenUrl + fileName);
            entity.setId(uuid);
            attachRepository.save(entity);

            AttachDTO attachDTO = new AttachDTO();
            attachDTO.setId(entity.getId());
            attachDTO.setOriginalName(file.getOriginalFilename());
            attachDTO.setUrl(attachOpenUrl + fileName);

            return attachDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] openGeneral(String fileName) {
        byte[] data;
        try {
            String[] arr = fileName.split("\\.");

            Optional<Attach> byId = attachRepository.findById(arr[0]);

            if (byId.isEmpty())
                throw new AttachOperationFailedException("attach.http not found id=" + arr[0]);

            Attach attach = byId.get();

            Path file = Paths.get(attachFolder +attach.getPath()+"/"+ fileName);
            data = Files.readAllBytes(file);

            return data;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return new byte[0];
    }

    public Attach getById(final String id){
        return attachRepository.findById(id).orElseThrow(()->{
           throw new NotFoundException("Attach not found!");
        });
    }
    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/12/21
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

}
