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
import akka.actor.ActorRef
import akka.actor.Props
import utils.GroupActor
import scala.concurrent.duration.FiniteDuration
import play.api.libs.concurrent.Akka
import play.libs.Akka
import akka.actor.ActorSystem
import java.util.concurrent.TimeUnit
import scala.concurrent.Future

@Singleton
class GroupController @Inject() (implicit ec: ExecutionContext) extends Controller with GroupRepository with PostgreDBComponent {

  def addGroupToDatabase: play.api.mvc.Action[play.api.mvc.AnyContent] = Action.async {
    //addGroup("Test").map { x => Ok(Json.toJson(x)) }
    val actor: ActorRef = ActorSystem("MySystem").actorOf(Props(new GroupActor), "actor");
    ActorSystem("MySystem").scheduler.schedule(new FiniteDuration(1L, TimeUnit.SECONDS), new FiniteDuration(1L, TimeUnit.SECONDS), actor, "Test");
    //ActorSystem("My system").scheduler.schedule(new FiniteDuration(1L, TimeUnit.SECONDS), new FiniteDuration(1L, TimeUnit.SECONDS))("Test", actor)

    Future.successful(Ok)
  }
}
