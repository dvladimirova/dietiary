package models

import play.api.libs.json._

case class Group(id: Option[Long], name: String)

object Group {
}
