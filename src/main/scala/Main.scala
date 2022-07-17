import com.raquo.laminar.api.L._
import org.scalajs.dom

object Main {
  def main(args: Array[String]): Unit = {
    val rootElement: HtmlElement = div(h1("Hello World"))
    val containerNode = dom.document.querySelector("#root")
    render(containerNode, rootElement)
  }
}
