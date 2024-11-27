package com.takseha.danari.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takseha.danari.R
import com.takseha.danari.data.dto.circle.Post
import com.takseha.danari.data.dto.circle.PostType
import com.takseha.danari.databinding.FragmentEventListBinding
import com.takseha.danari.presentation.adapter.EventRVAdapter

class EventListFragment : Fragment() {
    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!
    private lateinit var clubName: String

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clubName = requireActivity().intent.getStringExtra("clubName") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            backBtn.setOnClickListener {
                it.findNavController().popBackStack()
            }

            if (clubName == "Aegis") {
                setEventList(eventsAegis)
            } else if (clubName == "MUSE") {
                setEventList(eventsMuse)
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
            clickEventItem(eventRVAdapter, posts)
        }
    }

    private fun clickEventItem(
        eventRVAdapter: EventRVAdapter,
        posts: List<Post>
    ) {
        eventRVAdapter.onClickListener = object : EventRVAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val bundle = Bundle().apply {
                    putSerializable("event", posts[position])
                }
                view.findNavController().navigate(R.id.action_eventListFragment_to_eventInfoFramgent, bundle)
            }
        }
    }
}