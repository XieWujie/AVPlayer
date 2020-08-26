package com.example.main.http.entity
data class DiscoveryEntity(
    val code: Int,
    val `data`: Data,
    val message: String
)
data class Block(

    val action: String,
    val actionType: String,
    val blockCode: String,
    val canClose: Boolean,
    val creatives: List<Creative>,
    //val extInfo: ExtInfo,
    val showType: String,
    val uiElement: UiElementX
)

data class Data(
    val blockUUIDs: Any,
    val blocks: List<Block>,
    val cursor: String,
    val guideToast: GuideToast,
    val hasMore: Boolean,
    val pageConfig: PageConfig
)

data class Creative(
    val action: String,
    val actionType: String,
    val alg: String,
    val creativeId: String,
    val creativeType: String,
    val logInfo: String,
    val position: Int,
    val resources: List<Resource>,
    val uiElement: UiElement
)

data class Resource(
    val action: Any,
    val actionType: Any,
    val alg: Any,
    val logInfo: Any,
    val resourceExtInfo: ResourceExtInfo,
    val resourceId: String,
    val resourceType: String,
    val resourceUrl: Any,
    val uiElement: UiElement,
    val valid: Boolean
)

data class ResourceExtInfo(
    val highQuality: Boolean,
    val playCount: Long
)

data class UiElement(
    val image: Image,
    val mainTitle: MainTitle,
    val subTitle: SubTitle
)

data class Image(
    val imageUrl: String
)

data class MainTitle(
    val title: String
)

data class ExtInfo(
    val alg: Any,
    val log_info: Any,
    val moduleId: String,
    val moduleName: String,
    val orpheusUrl: String,
    val squareFeedViewDTOList: List<SquareFeedViewDTO>
)

data class SquareFeedViewDTO(
    val alg: String,
    val id: String,
    val log_info: Any,
    val position: Int,
    val reason: Any,
    val resource: ResourceX,
    val type: Int,
    val typeDesc: String
)

data class ResourceX(
    val mlogBaseData: MlogBaseData,
    val mlogExt: MlogExt,
    val mlogExtVO: MlogExtVO,
    val userProfile: UserProfile
)

data class MlogBaseData(
    val audio: Any,
    val audioDTO: Any,
    val coverColor: Long,
    val coverDynamicUrl: Any,
    val coverHeight: Int,
    val coverUrl: String,
    val coverWidth: Int,
    val id: String,
    val interveneText: String,
    val mlogLocation: Any,
    val mlogLocationDTO: Any,
    val talk: Talk,
    val text: String,
    val type: Int
)

data class Talk(
    val coverHeight: Int,
    val coverUrl: Any,
    val coverWidth: Int,
    val followed: Boolean,
    val follows: Int,
    val participations: Int,
    val rcmdInfo: Any,
    val shareCount: Int,
    val talkDesc: String,
    val talkId: Int,
    val talkName: Any,
    val type: Int
)

data class MlogExt(
    val commentCount: Int,
    val likedCount: Int,
    val rcmdInfo: Any,
    val shareCount: Int,
    val strongPushIcon: Any,
    val strongPushMark: Any
)

data class MlogExtVO(
    val commentCount: Int,
    val likedCount: Int,
    val rcmdInfo: Any,
    val shareCount: Int,
    val strongPushIcon: Any,
    val strongPushMark: Any
)

data class UserProfile(
    val avatarUrl: String,
    val followed: Boolean,
    val gender: Int,
    val nickname: String,
    val userId: Int,
    val userType: Int
)

data class UiElementX(
    val button: Button,
    val mainTitle: MainTitleX,
    val subTitle: SubTitle
)

data class Button(
    val action: String,
    val actionType: String,
    val iconUrl: Any,
    val text: String
)

data class MainTitleX(
    val title: String
)

data class SubTitle(
    val title: String
)

data class GuideToast(
    val hasGuideToast: Boolean,
    val toastList: List<Any>
)

data class PageConfig(
    val abtest: List<String>,
    val fullscreen: Boolean,
    val nodataToast: String,
    val refreshInterval: Int,
    val refreshToast: String,
    val songLabelMarkLimit: Int,
    val songLabelMarkPriority: List<String>,
    val title: Any
)
