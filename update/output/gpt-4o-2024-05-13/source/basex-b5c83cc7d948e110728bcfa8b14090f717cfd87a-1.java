import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.File;
import java.util.Locale;

public class ItemListTest {
  @Test
  public void resolvePath() {
    final String path = query(_FILE_RESOLVE_PATH.args(PATH1));
    final String can = new File(PATH1).getAbsoluteFile().getPath();
    assertEquals(path.toLowerCase(Locale.ENGLISH), can.toLowerCase(Locale.ENGLISH));
  }
}