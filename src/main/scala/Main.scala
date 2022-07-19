import SquaresVisualization.{Color, Coord, Square}
import com.raquo.airstream.timing.PeriodicEventStream
import com.raquo.laminar.api.L._
import org.scalajs.dom

object Main {
  def main(args: Array[String]): Unit = {
    val svgSize = 500
    val squareSize = 50
    val color = Color.black
    val range = 1 to svgSize by squareSize
    val coords = range.flatMap(x => range.map(y => Coord(x, y))).toList
    val squares = coords.map(coord => Square(squareSize, coord, color))
    val intervalMs = 10
    val squaresStream = new PeriodicEventStream[List[Square]](
      initial = squares,
      next = {
        case head :: next => Some((next, intervalMs))
        case Nil          => None
      },
      emitInitial = true,
      resetOnStop = false
    ).collect { case coord :: rest => coord }
    val svg = SquaresVisualization.svgElement(
      svgSize = svgSize,
      squaresStream = squaresStream
    )
    val rootElement: HtmlElement = div(svg)
    val containerNode = dom.document.querySelector("#root")
    render(containerNode, rootElement)
  }
}
