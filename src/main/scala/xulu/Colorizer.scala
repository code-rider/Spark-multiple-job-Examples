package xulu

import java.awt.Color

object Colorizer {

  def getColorizer(name: String): Colorizer = {
    name match {
      case "red" => new SimpleColorizer(255, 0, 0)
      case "green" => new SimpleColorizer(0, 255, 0)
      case "blue" => new SimpleColorizer(0, 0, 255)
      case "yellow" => new SimpleColorizer(255, 0, 0)
      case "multi" => new MultiColorColorizer()
      case default => new SimpleColorizer(255, 255, 255)
    }
  }

  trait Colorizer {
    def getColor(weight: Double): Color

    protected def normalizeColorComponent(c: Int): Int =
    {
      if (c < 0) {
        0
      } else if (c > 255) {
        255
      } else {
        c
      }
    }
  }

  private class SimpleColorizer(red: Int, green: Int, blue: Int) extends Colorizer {
    override def getColor(weight: Double): Color = {
      val r = (red * weight).toInt
      val g = (green * weight).toInt
      val b = (blue * weight).toInt

      new Color(
        normalizeColorComponent(r),
        normalizeColorComponent(g),
        normalizeColorComponent(b),
        200
      )
    }
  }

  private class MultiColorColorizer extends Colorizer {
    val s1 = 0.25d
    val s2 = 0.50d
    val s3 = 0.75d
    val s4 = 1.0d

    override def getColor(weight: Double): Color = {
      var r: Int = 0
      var g: Int = 0
      var b: Int = 0
      if (weight < s1) {
        // black to blue
        r = 0
        g = 0
        b = (255 * weight / s1).toInt
      } else if (weight < s2) {
        // blue to green
        r = 0
        g = (255 * (weight - s1) / (s2 - s1)).toInt
        b = (255 * (s2 - weight) / (s2 - s1)).toInt
      } else if (weight < s3) {
        // green to yellow
        r = (255 * (s3 - weight) / (s3 - s2)).toInt
        g = 255
        b = 0
      } else {
        // yellow to red
        r = (255 * (weight - s3) / (s4 - s3)).toInt
        g = (255 * (s4 - weight) / (s4 - s3)).toInt
        b = 0
      }

      new Color(
        normalizeColorComponent(r),
        normalizeColorComponent(g),
        normalizeColorComponent(b),
        200
      )
    }
  }

}