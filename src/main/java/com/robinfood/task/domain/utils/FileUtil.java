/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.utils;

import com.robinfood.task.domain.exception.FileErrorException;
import com.robinfood.task.domain.exception.generic.EMessageAplication;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author BobSuarez
 */
@Slf4j
public class FileUtil
{

  /**
   * Method that create folder
   *
   * @param nameFolder Name the folder
   * @return Path tarjet
   */
  public static String createFolder(String nameFolder)
  {
    File folderPath = new File("./" + nameFolder);
    if (folderPath.exists()) {
      log.error(" La carpeta ya existe {0}", folderPath);
      return folderPath.getPath();
    }
    if (folderPath.mkdir()) {
      log.error("Se ha creado la carpeta {0}", folderPath);
      return folderPath.getPath();
    }
    return folderPath.getPath();
  }

  @SuppressWarnings("CallToPrintStackTrace")
  public static String copyDirectoryOutResource(MultipartFile datFile, String folder, String identification)
          throws FileErrorException
  {
    var timeNow = LocalTime.now();
    StringBuilder nameFile = new StringBuilder();
    nameFile.append(identification)
            .append(".txt");
    try {
      var path = folder + "/" + nameFile.toString();
      File carpeta = new File(path);
      Files.copy(datFile.getInputStream(), carpeta.toPath(), StandardCopyOption.REPLACE_EXISTING);
      return nameFile.toString();
    } catch (Exception e) {
      throw new FileErrorException(EMessageAplication.FILE_BUILD, e);
    }

  }

  private static Path getPath(URI uri, String folder)
  {
    try {
      if (uri.getScheme().equals("jar")) {
        FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
        return fileSystem.getPath(folder);
      }
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return Paths.get(uri);
  }
}
