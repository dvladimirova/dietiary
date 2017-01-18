package controllers

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

import scala.collection.Seq
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext
import scala.math.BigDecimal.int2bigDecimal

import connection.PostgreDBComponent
import javax.inject.Inject
import javax.inject.Singleton
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import dal.GroupRepository

@Singleton
class GroupController @Inject() (implicit ec: ExecutionContext) extends Controller with GroupRepository with PostgreDBComponent {

  def addGroupToDatabase: play.api.mvc.Action[play.api.mvc.AnyContent] = Action.async {
    addGroup("Test").map { x => Ok(Json.toJson(x)) }
  }
}
