package connection

import slick.driver.PostgresDriver

trait PostgreDBComponent extends DBComponent {
  
  val driver = PostgresDriver

  import driver.api._

  val db: Database = PostgreDB.connectionPool

}

private[connection] object PostgreDB {

  import slick.driver.PostgresDriver.api._

  val connectionPool = Database.forConfig("postgre")

}