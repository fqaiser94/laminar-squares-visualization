import SquaresVisualization.{Color, Coord, Square}
import com.raquo.laminar.api.L._
import org.scalajs.dom

object Main {
  def main(args: Array[String]): Unit = {
    val svgSize = 500
    val squareSize = 450
    val color = Color.black
    val svg = SquaresVisualization.svgElement(
      svgSize = svgSize,
      square = Square(squareSize, Coord(25, 25), color)
    )
    val rootElement: HtmlElement = div(svg)
    val containerNode = dom.document.querySelector("#root")
    render(containerNode, rootElement)
  }
}
