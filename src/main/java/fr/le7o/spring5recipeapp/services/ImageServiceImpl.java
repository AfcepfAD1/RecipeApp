package fr.le7o.spring5recipeapp.services;

import fr.le7o.spring5recipeapp.domain.Recipe;
import fr.le7o.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

        log.info("Received a file");

        //Maj 162
        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            //Convertir le tableay de byte en tablea de Bytes
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
        }catch (IOException e){
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
