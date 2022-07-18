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
  }
  case class Square(size: Int, coord: Coord, color: Color)

  def makeSvgSquare(square: Square): ReactiveSvgElement[RectElement] =
    svg.rect(
      svg.className := "square",
      svg.x := square.coord.x.toString,
      svg.y := square.coord.y.toString,
      svg.height := square.size.toString,
      svg.width := square.size.toString,
      svg.stroke := Color.white.toRGB,
      svg.fill := square.color.toRGB
    )

  def svgElement(
      svgSize: Int,
      square: Square
  ): ReactiveSvgElement[SVG] =
    svg.svg(
      svg.height := svgSize.toString,
      svg.width := svgSize.toString,
      makeSvgSquare(square)
    )
}
