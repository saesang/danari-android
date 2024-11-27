package com.takseha.danari.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.Post
import com.takseha.danari.data.dto.circle.PostType
import com.takseha.danari.data.dto.circle.Review
import com.takseha.danari.databinding.FragmentCircleMainBinding
import com.takseha.danari.presentation.adapter.EventRVAdapter
import com.takseha.danari.presentation.adapter.RecruitRVAdapter
import com.takseha.danari.presentation.adapter.ReviewRVAdapter
import com.takseha.danari.presentation.viewmodel.CircleMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CircleMainFragment : Fragment() {
    private var _binding: FragmentCircleMainBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: CircleMainViewModel by viewModels()
    private lateinit var clubName: String
    private lateinit var description: String
    private lateinit var roomNumber: String

    val eventsAegis = listOf(
        Post(
            clubName = "Aegis",
            createdAt = "2024-11-12T",
            imageUrls = listOf(),
            postContent = "안녕하세요! \n" +
                    "이번에 Kitri에서 학교/동아리를 대상으로 화이트햇 스쿨 3기 모집설명회를 신청 받고있습니다. 이와 관련하여 저희 학교에서도 보안 및 해킹을 포함한 다양한 분야에 관심있는 분들을 대상으로 모집설명회를 진행하려고 합니다.\n" +
                    "화이트햇 스쿨 커리큘럼은 홈페이지에 나와있으니 궁금하신 분들은 참고하거나 저에게 개인적으로 편하게 물어보셔도 됩니다!\n" +
                    "본 행사가 기획되면 이지스 주관으로 행사가 진행되지만 동아리 소속이 아니신 분들도 참여 가능합니다!\n" +
                    "12월이 시험기간임을 고려해 날짜 선정한 점 양해부탁드립니다.\n" +
                    "이지스를 통해 신청하신 분들도 정확한 수요를 위해 다시 신청해주시면 감사하겠습니다!\n" +
                    "많은 관심 부탁드립니다! 신청은 금요일(15일)까지 입니다:)\n" +
                    "\n" +
                    "날짜 : 12월 21일 오후 14시\n" +
                    "장소 : 단국대학교 ICT관 (상세 장소는 추후 공지 예정)",
            postTitle = "화이트햇 스쿨 3기 모집설명회",
            postId = 0,
            username = "kimdan",
            postType = PostType.CLUB_EVENT
        ),
        Post(
            clubName = "Aegis",
            createdAt = "2024-09-21T",
            imageUrls = listOf(),
            postContent = "Aegis에서 개최하는 “졸업생 초청 세미나”에 많은 학생들의 참여 부탁드립니다.\n" +
                    "\n" +
                    "일시: 9/25(수) 18:30 – 20:50\n" +
                    "장소: 미디어센터 507호\n" +
                    "참여자: 세미나 주제에 관심있는 단국대학교 재학생 누구나\n" +
                    "\n" +
                    "발표자 및 내용\n" +
                    "1) 이현재: 대학일기 (대학교 당시 했던 활동들)\n" +
                    "2) 박찬호: 진입장벽 올라타기 전략\n" +
                    "3) 이주선: 문과생이었던 내가 지금은 해커?\n" +
                    "\n" +
                    "자세한 사항은 첨부한 홍보물 참고하시길 바랍니다.\n" +
                    "고맙습니다.",
            postTitle = "졸업생 초청 세미나",
            postId = 1,
            username = "kimdan",
            postType = PostType.CLUB_EVENT
        )
    )
    val recruitmentsAegis = listOf(
        Post(
            clubName = "Aegis",
            createdAt = "2024-08-07T",
            imageUrls = listOf(),
            postContent = "[Aegis 신규 회원 모집]\n" +
                    "안녕하세요.\n" +
                    "2024년 2학기 Aegis 회장 정현우입니다.\n" +
                    "2024년도 2학기를 함께할 Aegis 회원님들을 모집합니다.\n" +
                    "\n" +
                    "✔Aegis는 컴퓨터를 공부하기를 원하는 학생들이 모여 연구 및 학습하는 학술동아리입니다.\n" +
                    "- 개발, 보안을 희망하는 학생들이 모여 서로 공부하고 연구 및 공유를 지향합니다.\n" +
                    "\n" +
                    "✔Aegis는 컴퓨터와 관련된 다양한 활동을 진행합니다.\n" +
                    "1. 인근 학교 학생들을 대상으로 코딩 수업을 진행하는 봉사활동\n" +
                    "2. Aegis 졸업자들을 초청하여 진행하는 세미나\n" +
                    "3. 해킹대회와 코딩대회 또는 해커톤\n" +
                    "4. 컴퓨터 기초부터 심화까지 다양한 주제의 스터디\n" +
                    "5. 대외 컨퍼런스 또는 대회 참여\n" +
                    "\n" +
                    "✔컴퓨터를 하나도 몰라도 컴퓨터에 관심과 흥미가 있는 모든 단국대학교 학생들은 언제나 가입이 가능합니다.\n" +
                    "\n" +
                    "✔ 2학기 10,000원의 회비를 받고 있습니다.\n" +
                    "\n" +
                    "✔ 동아리 회칙 - 회칙 꼭 읽어주시면 감사하겠습니다!\n" +
                    "https://dk-aegis.org/?page_id=235\n" +
                    "\n" +
                    "✔ 가입 시 별도의 면접이나 제한사항은 절대 없습니다!\n" +
                    "✔ 누구나 가입 가능합니다 :)\n" +
                    "\n" +
                    "✔ 문의\n" +
                    "1. 회장 정현우 010-9845-3537\n" +
                    "2. 동아리방 : 혜당관 530호\n" +
                    "3. 인스타그램 : @dku_aegis\n" +
                    "4. 동아리 홈페이지 : https://dk-aegis.org\n" +
                    "\n" +
                    "가입 링크\n" +
                    "https://forms.gle/o2yLyC9iSnrPzfrw6",
            postTitle = "2024년 2학기 신규 회원 모집",
            postId = 0,
            username = "kimdan",
            postType = PostType.CLUB_RECRUITMENT
        )
    )
    val eventsMuse = listOf(
        Post(
            clubName = "MUSE",
            createdAt = "2024-10-25T",
            imageUrls = listOf(),
            postContent = "단국대학교 역사상 최고의 실력과 폼을 유지하는 두 밴드 동아리가 연합 공연을 진행합니다!!\n" +
                    "\n" +
                    "시간: 2024년 11월 23일(토) 오후 5시\n" +
                    "\n" +
                    "장소: 분당 모던 K 실용음악학원 공연장(경기도 성남시 분당구 야탑동 354-4)\n" +
                    "\n" +
                    "<티켓 가격>\n" +
                    "사전 예매: 1인 5,000원\n" +
                    "현장 예매: 1인 7,000원\n" +
                    "\n" +
                    "예매폼: https://form.naver.com/response/7JvPaUAOVXa8vZS5UUdLcg\n" +
                    "\n" +
                    "* 블랙베어즈&뮤즈의 클럽공연 사전 예매 링크입니다. 사전 예매 시 할인 헤택이 있습니다. 현장에서 이름과 전화번호를 확인 후 티켓을 배분할 예정입니다.\n" +
                    "\n" +
                    "Poster: @h.y_graphic @lapiz_.__",
            postTitle = "2024 Black Bears X Muse 연합 공연",
            postId = 0,
            username = "mumalife",
            postType = PostType.CLUB_EVENT
        )
    )
    val recruitmentsMuse = listOf(
        Post(
            clubName = "MUSE",
            createdAt = "2024-08-05T",
            imageUrls = listOf(),
            postContent = "\uD83D\uDCE2안녕하세요 단국대학교(죽전) 중앙동아리 밴드 ‘MUSE’입니다. 신입부원 모집 안내 및 오디션 공지 드립니다!\uD83D\uDCE2\n" +
                    "\uD83D\uDD25올해 마지막 신입부원 모집\uD83D\uDD25\n" +
                    "\n" +
                    "\uD83C\uDF1F모집 글 아래에 오디션 공지가 있으니 끝까지 읽어주시기 바랍니다!\n" +
                    "\n" +
                    "\uD83D\uDCCD활동계획:\n" +
                    "교내버스킹, 정기 공연, 학교 축제 공연, 홍대 라이브홀 대관 공연등을 진행하고 있습니다. 이번 2학기에는 동아리 알림제, 창립제 공연, 가을 정기공연, 대학연합공연, 홍대공연 등이 예정되어 있습니다!\n" +
                    "\n" +
                    "\uD83D\uDCCD모집분야: 보컬, 기타, 베이스, 드럼, 키보드\n" +
                    "특히 \uD83C\uDFB9키보드\uD83C\uDFB9 선생님들 격하게 환영합니다\uD83E\uDD79\n" +
                    "\n" +
                    "\uD83D\uDCCD1차 모집기간: 종료\n" +
                    "\n" +
                    "\uD83D\uDCCD2차 모집기간:\n" +
                    "8월 26일 ~ 9월 5일\n" +
                    "알림제 끝나는 날 6시까지 지원 받겠습니다!\n" +
                    "\n" +
                    "\uD83D\uDCCD지원방법:\n" +
                    "알림제 당일 부스에서 지원 가능\n" +
                    "\n" +
                    "\uD83D\uDCCD오디션 참고사항:\n" +
                    "<지정곡 안내>\n" +
                    "\n" +
                    "\uD83C\uDFA4보컬\n" +
                    "남자: YB-박하사탕 + 외국 밴드곡 1곡\n" +
                    "OR\n" +
                    "MUSE - time is running out + 자유곡 1곡\n" +
                    "\n" +
                    "여자: 체리필터-낭만고양이 + 자유곡 1곡\n" +
                    "\n" +
                    "\uD83C\uDFB8기타\n" +
                    "Sum 41 - Hell Song(인트로~기타솔로) + 자유곡 하나(필수 아님)\n" +
                    "\n" +
                    "\uD83E\uDD41드럼\n" +
                    "드럼 패드로 4, 8, 16비트, 셋잇단음표 각각 2마디씩 치면서 100-140bpm 템포업 + 자유곡 1절\n" +
                    "\n" +
                    "\uD83C\uDFB8베이스\n" +
                    "Muse - time is running out(1절) + 자유곡 하나(필수 x)\n" +
                    "\n" +
                    "\uD83C\uDFB9키보드\n" +
                    "Toto - Hold the line, 윤하 - 혜성 중 택1 + 자유곡(필수)\n" +
                    "\n" +
                    "\uD83C\uDFB5 세션별 기타 사항:\n" +
                    "\n" +
                    "1.\n" +
                    "보컬:mr로 진행 기타&베이스&드럼&키보드:메트로놈 틀고 진행\n" +
                    "\n" +
                    "2.\n" +
                    "오디션 후, 간단한 질문이 있을 예정입니다.\n" +
                    "\n" +
                    "3.\n" +
                    "각 세션별 오디션 날짜는 따로 공지하겠습니다. 궁금한 점은 연락 주시면 안내해드리겠습니다.\n" +
                    "\n" +
                    "2차 오디션은\n" +
                    "9월 6일 ~ 9월 10일 사이에 진행합니다!\n" +
                    "\n" +
                    "장소: 혜당관 604호에서 대기, 606호에서 오디션 진행\n" +
                    "\n" +
                    "\uD83E\uDD0D음악을 사랑하시는 분\n" +
                    "\n" +
                    "\uD83E\uDD0D악기 연주를 좋아하시는 분\n" +
                    "\n" +
                    "\uD83E\uDD0D자신의 끼를 발산하고 싶은 분\n" +
                    "\n" +
                    "\uD83E\uDD0D함께 음악얘기를 나눌 수 있는 친구가 필요한 분\n" +
                    "\n" +
                    "\uD83E\uDD0D밴드음악을 좋아하시는 분\n" +
                    "\n" +
                    "이라면 환영입니다!\n" +
                    "\n" +
                    "뮤즈와 함께 해요\uD83E\uDEF6\uD83C\uDFFB\n" +
                    "궁금하신 점이 있으시다면 아래 연락처로 연락 주시거나 혜당관 <604호>를 찾아주세요!!\n" +
                    "분위기 정말 좋으니 동방 한 번 방문해주세요!!\uD83D\uDE04\n" +
                    "\n" +
                    "가입문의: 장준익 010-8211-1307\n" +
                    "인스타:dku_muse\n" +
                    "(인스타를 참고하시면 그동안 진행했던 공연영상을 볼 수 있습니다!\uD83D\uDC30)",
            postTitle = "MUSE 34기 신입부원 모집 (마지막)",
            postId = 0,
            username = "mumalife",
            postType = PostType.CLUB_RECRUITMENT
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubName = requireActivity().intent.getStringExtra("clubName") ?: ""
        description = requireActivity().intent.getStringExtra("description") ?: ""
        roomNumber = requireActivity().intent.getStringExtra("roomNumber") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCircleMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            backBtn.setOnClickListener {
                requireActivity().finish()
            }
            circleName.text = clubName
            circleDesc.text = description
            circleLocation.text = roomNumber

//            viewmodel.getCircleFullInfo(clubName)
//            viewLifecycleOwner.lifecycleScope.launch {
//                viewmodel.uiState.collectLatest {
//
//                    setEventList(it.events)
//                    setRecruitList(it.recruitments)
//                    setReviewList(it.reviews)
//
//                    if (it.events.isEmpty()) {
//                        noEventText.visibility = VISIBLE
//                    } else {
//                        noEventText.visibility = GONE
//                    }
//
//                    if (it.recruitments.isEmpty()) {
//                        noRecruitText.visibility = VISIBLE
//                    } else {
//                        noRecruitText.visibility = GONE
//                    }
//
//                    if (it.reviews.isEmpty()) {
//                        noReviewText.visibility = VISIBLE
//                    } else {
//                        noReviewText.visibility = GONE
//                    }
//                }
//            }

            if (clubName == "Aegis") {
                setEventList(eventsAegis)
                setRecruitList(recruitmentsAegis)
            } else if (clubName == "MUSE") {
                setEventList(eventsMuse)
                setRecruitList(recruitmentsMuse)
            } else {
                noEventText.visibility = VISIBLE
                noRecruitText.visibility = VISIBLE
            }

            viewmodel.getReviewList(clubName)
            viewLifecycleOwner.lifecycleScope.launch {
                viewmodel.reviewState.collectLatest {
                    if (it.isEmpty()) {
                        noReviewText.visibility = VISIBLE
                    } else {
                        noReviewText.visibility = GONE
                    }
                    setReviewList(it)
                }
            }

            moreEventBtn.setOnClickListener {
                it.findNavController().navigate(R.id.action_circleMainFragment_to_eventListFragment)
            }
            moreRecruitBtn.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_circleMainFragment_to_recruitListFragment)
            }
            moreReviewBtn.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_circleMainFragment_to_reviewListFragment)
            }

        }
    }

    private fun setEventList(
        posts: List<Post>
    ) {
        with(binding) {
            val eventRVAdapter =
                EventRVAdapter(requireContext(), posts)
            eventList.adapter = eventRVAdapter
            eventList.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setRecruitList(
        recruits: List<Post>
    ) {
        with(binding) {
            val recruitRVAdapter =
                RecruitRVAdapter(requireContext(), recruits)

            recruitList.adapter = recruitRVAdapter
            recruitList.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setReviewList(
        reviews: List<Review>
    ) {
        with(binding) {
            val reviewRVAdapter =
                ReviewRVAdapter(requireContext(), reviews)

            reviewList.adapter = reviewRVAdapter
            reviewList.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}