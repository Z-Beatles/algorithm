package cn.waynechu.sort.selection;

import cn.waynechu.AlgoVisHelper;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 20:08
 */
public class SelectionSortData {
    private Member[] members;

    public SelectionSortData(int n, int randomBound) {
        this.members = new Member[n];
        Random random = new Random();
        Member member;
        for (int i = 0; i < n; i++) {
            member = new Member();
            member.setNumber(random.nextInt(randomBound) + 1);
            // 初始化颜色为灰色
            member.setColor(AlgoVisHelper.Grey);
            members[i] = member;
        }
    }

    public Member[] getMembers() {
        return members;
    }

    /**
     * 交换index为i和j的两个member对象
     **/
    public void swap(int i, int j) {
        Member tmp = members[i];
        members[i] = members[j];
        members[j] = tmp;
    }
}
