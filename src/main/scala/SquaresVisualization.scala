import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom.svg.{RectElement, SVG}

object SquaresVisualization {
  case class Coord(x: Int, y: Int)
  case class Color(r: Int, g: Int, b: Int) {
    val toRGB = s"rgb($r,$g,$b)"
  }
  object Color {
    val white: Color = Color(255, 255, 255)
    val black: Color = Color(0, 0, 0)
    def range(
        startColor: Color,
        endColor: Color,
        range: Int = 100
    ): List[Color] = {
      val incrementFactorR = (endColor.r - startColor.r) / range
      val incrementFactorG = (endColor.g - startColor.g) / range
      val incrementFactorB = (endColor.b - startColor.b) / range

      (0 to range).map { idx =>
        Color(
          r = startColor.r + (incrementFactorR * idx),
          g = startColor.g + (incrementFactorG * idx),
          b = startColor.b + (incrementFactorB * idx)
        )
      }.toList
    }
  }
  case class Square(size: Int, coord: Coord, colorStream: EventStream[Color])

  def makeSvgSquare(square: Square): ReactiveSvgElement[RectElement] =
    svg.rect(
      svg.className := "square",
      svg.x := square.coord.x.toString,
      svg.y := square.coord.y.toString,
      svg.height := square.size.toString,
      svg.width := square.size.toString,
      svg.stroke := Color.white.toRGB,
      svg.fill <-- square.colorStream.map(_.toRGB)
    )

  def svgElement(
      svgSize: Int,
      squaresStream: EventStream[Square]
  ): ReactiveSvgElement[SVG] =
    svg.svg(
      svg.height := svgSize.toString,
      svg.width := svgSize.toString,
      children <-- squaresStream
        .map(makeSvgSquare)
        .foldLeft(List.empty[ReactiveSvgElement[RectElement]])((acc, square) => acc :+ square)
    )
}
