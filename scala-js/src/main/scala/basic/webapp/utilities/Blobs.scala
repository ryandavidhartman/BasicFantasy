package basic.webapp.utilities

import org.scalajs.dom.{Blob, BlobPropertyBag}

import scala.scalajs.js
import scala.scalajs.js.typedarray._

object Blobs {

  def fromBytes(data: Array[Byte], contentType: String = ""): Blob = {
    val buffer = data.toTypedArray.buffer
    new Blob(js.Array(buffer), new BlobPropertyBag { `type` = contentType })
  }

}
