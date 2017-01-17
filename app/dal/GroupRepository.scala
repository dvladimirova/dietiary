package dal

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import slick.ast.TableExpansion
import java.util.Date

import java.nio.ByteBuffer
import java.nio.ByteOrder
import connection.DBComponent
import models.Group

trait GroupRepository extends GroupTable { this: DBComponent =>

  import driver.api._

  def getAllGroups: Future[Seq[Group]] = db.run {
    groupTableQuery.result
  }

  def addGroup(name: String): Future[Group] = {
    val groupWithId = (groupTableQuery returning groupTableQuery.map(_.id)
      into ((group, id) => group.copy(id = Some(id)))) += Group(None, name)
    db.run(groupWithId)
  }
}

private[dal] trait GroupTable { this: DBComponent =>

  import driver.api._

  private[GroupTable] class GroupTable(tag: Tag) extends Table[Group](tag, "groups") {

    def id = column[Long]("id", O.AutoInc, O.PrimaryKey)
    def name = column[String]("name")

    override def * = (id.?, name) <> ((Group.apply _).tupled, Group.unapply)
  }

  protected val groupTableQuery = TableQuery[GroupTable]
}
