package models

import play.api.libs.json._

case class Group(id: Option[Long], name: String)

object Group {
  implicit val groupFormat = new Writes[Group] {
    override def writes(group: Group) = Json.obj(
      "id" -> group.id,
      "name" -> group.name)
  }
}
