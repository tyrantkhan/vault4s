package com.onekhan.vault4s

import cats.effect.Effect
import com.onekhan.vault4s.api.v1.{SystemBackendHealth, SystemBackendSeal}
import org.http4s.client.Client

case class VaultClient[F[_]](httpClient: Client[F], address: org.http4s.Uri)(implicit
    ev: Effect[F]) {
  implicit val vc = this

  object api {
    object v1 {
      object sys {
        def seal = new SystemBackendSeal[F]()
        def health = new SystemBackendHealth[F]().health
      }
    }
  }
}
