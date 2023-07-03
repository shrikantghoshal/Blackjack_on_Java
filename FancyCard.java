import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javafx.scene.image.Image;


/**
 * Fancier version of Card which can provide images - e.g. for use
 * in card game applications that have a GUI.
 *
 * <p>Provided for use in COMP1721 Coursework 2 (advanced solution).</p>
 *
 * @author Nick Efford
 */
public class FancyCard extends Card {

  private static Map<String,Image> images = new HashMap<>();

  static {
    try (JarFile jar = new JarFile("images.jar")) {
      for (Suit suit : Suit.values()) {
        for (Rank rank : Rank.values()) {
          String card = String.format("%c%c", rank.getSymbol(), suit.getSymbol());
          String filename = card + ".png";
          JarEntry entry = jar.getJarEntry(filename);
          Image image = new Image(jar.getInputStream(entry));
          images.put(card, image);
        }
      }
    }
    catch (IOException error) {
      // Do nothing if images cannot be loaded
    }
  }

  /**
   * Creates a FancyCard object.
   *
   * @param rank Rank of the card
   * @param suit Suit of the card
   */
  public FancyCard(Rank rank, Suit suit) {
    super(rank, suit);
  }

  /**
   * Creates a FancyCard object, given its name as a string.
   *
   * @param name Name of card
   * @throws IllegalArgumentException if string is invalid
   * @see Card#Card(String)
   */
  public FancyCard(String name) {
    super(name);
  }

  /**
   * Provides the bitmap image associated with this card.
   *
   * @return This card's image
   */
  public Image getImage() {
    return images.get(this.toString());
  }
}
