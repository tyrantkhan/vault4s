package com.onekhan.vault4s

import java.util.concurrent.Executors

import cats.effect.{Blocker, ContextShift, IO, Timer}
import org.http4s.client.JavaNetClientBuilder
import org.specs2.mutable.Specification
import org.http4s.implicits._

import scala.concurrent.ExecutionContext.global

class Vault4sSpec extends Specification {
  implicit val cs: ContextShift[IO] = IO.contextShift(global)
  implicit val timer: Timer[IO] = IO.timer(global)

  val blockingPool = Executors.newFixedThreadPool(5)
  val blocker = Blocker.liftExecutorService(blockingPool)
  val httpClient = JavaNetClientBuilder[IO](blocker).create
  val address = uri"http://vault.localhost"
  val vaultMasterKey = "ac93362a3b88653d1a481c56a86788661b76cfe6866d92b7203c3ad264d0a6ce"

}
