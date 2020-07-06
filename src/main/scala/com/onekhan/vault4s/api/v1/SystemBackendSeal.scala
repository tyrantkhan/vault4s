package com.onekhan.vault4s.api.v1

import cats.effect.Effect
import com.onekhan.vault4s.VaultClient
import com.onekhan.vault4s.api.v1.SystemBackendSeal.SealResponse
import org.http4s.Method.PUT
import org.http4s.client.dsl.Http4sClientDsl
import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}
import io.circe.Json
import org.http4s.circe._

class SystemBackendSeal[F[_]](implicit ev: Effect[F], vc: VaultClient[F])
    extends Http4sClientDsl[F] {
  implicit val healthDecoder = jsonOf[F, SealResponse]

  def unseal(shard: String): F[SealResponse] = {
    val fields = List(("key", Json.fromString(shard)))
    val json = Json.fromFields(fields)
    val request = PUT(
      json,
      vc.address / "v1" / "sys" / "unseal"
    )

    vc.httpClient.fetchAs[SealResponse](request)
  }

}
object SystemBackendSeal {
  protected implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

  @ConfiguredJsonCodec
  case class SealResponse(
      `sealed`: Boolean,
      t: Double,
      n: Double,
      progress: Double,
      version: String,
      clusterName: String,
      clusterId: String
  )

}
