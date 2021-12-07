package ekrem.ozan.satellites.data

interface Mapper<ResponseModel, UiModel> {
    fun toMapUiModel(model: ResponseModel): UiModel
}
