package com.example.jee.projetjee.actions;

import com.example.jee.projetjee.data.Category;
import com.example.jee.projetjee.data.Science;
import com.example.jee.projetjee.repositories.ScienceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class  ScienceActions {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ScienceRepository scienceRepository;

    public void updateScienceImage(long id, String image) {
        Optional<Science> science = scienceRepository.findById(id);
        science.ifPresent(s -> {
            s.setImage(image);
            scienceRepository.save(s);
        });
    }

    public void updateScienceName(long id, String name) {
        Optional<Science> science = scienceRepository.findById(id);
        science.ifPresent(s -> {
            s.setName(name);
            scienceRepository.save(s);
        });
    }

    public void updateSciencePrice(long id, double price) {
        Optional<Science> science = scienceRepository.findById(id);
        science.ifPresent(s -> {
            s.setPrice(price);
            scienceRepository.save(s);
        });
    }

    public void updateScienceStock(long id, int stock) {
        Optional<Science> science = scienceRepository.findById(id);
        science.ifPresent(s -> {
            s.setStock(stock);
            scienceRepository.save(s);
        });
    }

    public void updateScienceCategory(long id, Category category) {
        Optional<Science> science = scienceRepository.findById(id);
        science.ifPresent(s -> {
            s.setCategory(category);
            scienceRepository.save(s);
        });
    }

    public void updateScienceAll(long id, String image, String name , double price, int stock, Category category) {
        Optional<Science> science = scienceRepository.findById(id);
        science.ifPresent(s -> {
            if (image != null) {
                s.setImage(image);
            }
            if (name != null) {
                s.setName(name);
            }
            if (price >= 0) {
                s.setPrice(price);
            }
            if (stock >= 0) {
                s.setStock(stock);
            }
            if (category != null) {
                s.setCategory(category);
            }
            scienceRepository.save(s);
        });
    }

    public void deleteScience(long id) {
        scienceRepository.deleteById(id);
    }


}
