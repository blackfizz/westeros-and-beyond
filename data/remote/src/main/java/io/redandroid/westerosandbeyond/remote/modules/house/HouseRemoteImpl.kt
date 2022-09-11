package io.redandroid.westerosandbeyond.remote.modules.house

import io.redandroid.westerosandbeyond.repository.contracts.remote.HouseRemote
import javax.inject.Inject

class HouseRemoteImpl @Inject constructor(
    private val api: HouseApi
): HouseRemote {
}