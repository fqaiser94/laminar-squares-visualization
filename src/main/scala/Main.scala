import SquaresVisualization.{Color, Coord, Square}
import com.raquo.laminar.api.L._
import org.scalajs.dom

object Main {
  def main(args: Array[String]): Unit = {
    val svgSize = 500
    val squareSize = 50
    val color = Color.black
    val squares = Seq(
      Square(squareSize, Coord(0, 0), color),
      Square(squareSize, Coord(450, 450), color)
    )
    val squaresStream = EventStream.fromSeq(squares)
    val svg = SquaresVisualization.svgElement(
      svgSize = svgSize,
      squaresStream = squaresStream
    )
    val rootElement: HtmlElement = div(svg)
    val containerNode = dom.document.querySelector("#root")
    render(containerNode, rootElement)
  }
}
