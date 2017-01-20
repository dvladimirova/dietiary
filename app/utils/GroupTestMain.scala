package utils

import dal.GroupRepository
import connection.DBComponent
import connection.PostgreDBComponent
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.ActorRef
import play.libs.Akka
import akka.actor.Props
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit
import akka.actor.Scheduler
import akka.actor.ActorSystem

object GroupTestMain extends App {
  val actor: ActorRef = Akka.system().actorOf(Props(new GroupActor), "actor");
  Akka.system().scheduler.schedule(new FiniteDuration(1L, TimeUnit.SECONDS), new FiniteDuration(1L, TimeUnit.SECONDS), actor, "Test");
  //ActorSystem("My system").scheduler.schedule(new FiniteDuration(1L, TimeUnit.SECONDS), new FiniteDuration(1L, TimeUnit.SECONDS))("Test", actor)
}
