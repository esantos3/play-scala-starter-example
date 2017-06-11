package dao

import java.sql.Date

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject

import models.Transaction
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

class TransactionDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val Transactions = TableQuery[TransactionsTable]

  def all: Future[Seq[Transaction]] = db.run(Transactions.result)

  private class TransactionsTable(tag: Tag) extends Table[Transaction](tag, "transactions") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def candidate = column[String]("candidate")

    def contributor = column[String]("contributor")

    def contributorState = column[String]("contributor_state")

    def contributorOccupation = column[Option[String]]("contributor_occupation")

    def amount = column[Long]("amount")

    def date = column[Date]("date")

    def * = (id.?, candidate, contributor, contributorState, contributorOccupation,
      amount, date) <> (Transaction.tupled, Transaction.unapply)
  }
}