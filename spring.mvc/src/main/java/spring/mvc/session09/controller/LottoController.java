package spring.mvc.session09.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lotto")
public class LottoController {
	private List<Set<Integer>> lottos = new CopyOnWriteArrayList<>();

	// Lotto 主畫面
	// @GetMapping("/")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("lottos", lottos); // 樂透號碼歷史紀錄
		return "session09/lotto";
	}

	// 取得最新電腦選號
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(Model model) {
		// 取得樂透號碼
		Set<Integer> lotto = getRandomLotto();
		// 將最新樂透號碼放在歷史紀錄中
		lottos.add(lotto);
		// 將必要資料傳給 jsp 呈現/處理
		model.addAttribute("lotto", lotto); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 樂透號碼歷史紀錄
		return "session09/lotto";
	}

	// 更新指定位置的電腦選號
	@RequestMapping(value = "/update/{index}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable("index") int index) {
		// 重新取得樂透號碼
		Set<Integer> lotto = getRandomLotto();
		// 更新指定位置的樂透號碼
		lottos.set(index, lotto);
		// 將必要資料傳給 jsp 呈現/處理
		model.addAttribute("lotto", lotto); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 樂透號碼歷史紀錄
		return "redirect:/mvc/lotto/";
	}

	// 刪除指定位置的電腦選號
	@RequestMapping(value = "/delete/{index}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("index") int index) {
		// 刪除指定位置的樂透號碼
		lottos.remove(index);
		// 將必要資料傳給 jsp 呈現/處理
		model.addAttribute("lotto", null); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 樂透號碼歷史紀錄
		return "redirect:/mvc/lotto/";
	}

	// 隨機生成最新電腦選號
	private Set<Integer> getRandomLotto() {
		Random r = new Random();
		// 樂透 539, 1~39不重複數字取5個
		Set<Integer> lotto = new LinkedHashSet<>();
		while (lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1);
		}
		return lotto;
	}

	@GetMapping("/stat")
	public String getStat(Model model) {
		Map<Integer, Long> basic = lottos.stream().flatMap(l -> l.stream())
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		
		Map<Integer, Long> stat = new LinkedHashMap<>();
		basic.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByKey())
								 .forEachOrdered(e -> stat.put(e.getKey(), e.getValue()));

		

		Map<Integer, Long> stat2 = new LinkedHashMap<>();
		basic.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
		 						 .forEachOrdered(e -> stat2.put(e.getKey(), e.getValue()));

		model.addAttribute("lotto", null); // 最新電腦選號
		model.addAttribute("lottos", lottos); // 樂透號碼歷史紀錄
		model.addAttribute("stat", stat);
		model.addAttribute("stat2", stat2);

		return "session09/lotto";
	}

}
