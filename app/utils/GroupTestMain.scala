package utils

import dal.GroupRepository
import connection.DBComponent
import connection.PostgreDBComponent
import scala.concurrent.ExecutionContext.Implicits.global

object GroupTestMain extends App with PostgreDBComponent with GroupRepository {
  addGroup("Test").map { x => println(x.id) }
}
