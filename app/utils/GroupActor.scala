package utils

import akka.actor.UntypedActor
import dal.GroupRepository
import connection.PostgreDBComponent
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class GroupActor @Inject() (implicit ec: ExecutionContext) extends UntypedActor with GroupRepository with PostgreDBComponent {

  override def onReceive(message: Any): Unit = {
    addGroup(message.asInstanceOf[String]).map { x => println(x.id) }
  }
}
