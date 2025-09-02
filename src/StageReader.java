import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {
  public static Stage readStage(String path) throws IOException {
    Stage stage = new Stage();
    List<String> lines = Files.readAllLines(Paths.get(path));
    return new Stage();
  }

}
/*
 * try {
 *  readFromFile("stage1.rvb");
 * }
 * catch (FileNotFoundException e){
 *  if (file == null)
 *  System.out.println("files not found");
 * }
 * 
 * catch (IOException e){
 *  if (grid.row != ...)
 * }
 * 
 * catch (Exception e){
 *   if (0 < row || row > 21)
 *      System.out.println("out of bonder");
 * }
 */