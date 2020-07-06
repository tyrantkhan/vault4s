package com.onekhan.vault4s

class VaultHealthSpec extends Vault4sSpec {
  val vaultClient = VaultClient(httpClient, address)
  val sealResponse = vaultClient.api.v1.sys.seal.unseal(vaultMasterKey).unsafeRunSync()
  val healthResponse = vaultClient.api.v1.sys.health.unsafeRunSync()

  "Vault Init" should {
    "be initialized" in {
      healthResponse.initialized == true
    }
    "be unsealed" in {
      healthResponse.`sealed` == false
    }
  }

}
