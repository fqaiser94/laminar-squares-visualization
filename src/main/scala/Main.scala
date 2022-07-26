import SquaresVisualization.{Color, Coord, Square}
import com.raquo.airstream.timing.PeriodicEventStream
import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val svgSize = 500
    val squareSize = 50
    val colors = Color.range(Color.black, Color.white)
    def colorStream: EventStream[Color] = {
      val intervalMs = 100
      new PeriodicEventStream[List[Color]](
        colors,
        {
          case head :: next => Some((next, intervalMs))
          case Nil          => None
        },
        true,
        false
      ).collect { case head :: rest => head }
    }
    val range = 1 to svgSize by squareSize
    val coords = range
      .flatMap(x => range.map(y => Coord(x, y)))
      .toList
      .sortBy(_ => Random.nextInt(10))
    val squares = coords.map(coord => Square(squareSize, coord, colorStream))
    val intervalMs = 100
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
