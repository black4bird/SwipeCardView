package online.osslab.demo.swipecardview;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import online.osslab.SwipeCardView;

public class MainActivity extends AppCompatActivity implements SwipeCardView.onSwipeListener,
        SwipeCardView.OnItemClickListener {

    String [] portraits = {
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-fYhn1e.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-KAkS7u.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-tWWep2.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-V6PFZS.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-tNO804.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-ByBBLv.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-TmswpZ.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-hiX1we.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-blKAZe.jpg",
            "http://img.mingxing.com/upload/thumb/2015/01-04/0-225Ofw.jpg"
    };

    String[] names = {"杨幂", "唐嫣", "范冰冰", "刘诗诗", "刘亦菲", "高圆圆", "柳岩", "孙俪", "古力娜扎", "李小璐"};

    String[] citys = {"北京", "上海", "青岛", "北京", "武汉", "北京", "衡阳", "上海", "新疆", "北京"};

    String[] signs = {"处女座", "射手座", "处女座", "双鱼座", "处女座", "天秤座", "天蝎座", "天秤座", "金牛座", "天秤座"};

    String[] heights = {"168", "170", "168", "165", "170", "165", "164", "165", "172", "164"};

    String[] weights = {"45", "43", "55", "44", "48", "48", "50", "45", "50", "42"};

    String[] profiles = {
            "2006年因出演《神雕侠侣》中“小东邪”郭襄而受到观众的关注，2009年又凭借《仙剑奇侠传三》而人气飙升。2011年因为在穿越剧《宫》中扮演洛晴川一角而获得了更高的人气。2012年发行了音乐专辑《亲幂关系ClosetoMe》，同年成立了杨幂工作室。之后由她主演的《如意》、《命运交响曲》、《盛夏晚晴天》等电视剧均获得不俗的收视率。2013年随着《小时代》、《小时代：青木时代》的上映杨幂在电影中的表现被赞超大惊喜，得到了不少观众及业内人士的好评。",
            "唐嫣，1983年12月6日出生于上海，中国女演员。2006年毕业于中央戏剧学院表演系本科班。2004年被张艺谋钦定为“奥运宝贝”，赴雅典参与“中国8分钟”的闭幕式表演。2007年出演《诛仙》MV女主角陆雪琪而备受关注。2009年因《仙剑奇侠传三》中紫萱一角人气飙升，后主演《夏家三千金》、《爱情睡醒了》、《X女特工》等电视剧。2012年成立唐嫣工作室，担任其主演微电影《逐爱之旅》的制作人。2014年凭借《金玉良缘》获得中国大学生电视节最受欢迎女演员奖。2015年寒假唐嫣主演的《何以笙箫默》、《千金女贼》、《活色生香》陆续播出，均取得不俗成绩。",
            "1998年参演琼瑶剧《还珠格格》一举成名。2004年凭借电影《手机》成为首位大众电影百花奖最佳演员的80后得主。2007年成立范冰冰工作室，签约旗下艺人，并制作出《胭脂雪》和《金大班》等电视剧。2010年主演《观音山》荣获东京国际电影节最佳女演员奖，2011年担任东京国际电影节评委。2013年戛纳国际电影节萧邦珠宝举办“范冰冰之夜”，并授予范冰冰“年度最佳国际艺人”称号。2014年主演的古装剧《武媚娘传奇》播出；并成为中国内地首部网络点击量超过100亿的电视剧；同年参演美国动作片《X战警：未来昔日》。2015年主演的好莱坞电影《日月人鱼》、《绝地逃亡》即将上映。",
            "刘诗诗（1987年3月10日—），原名刘诗施，中国内地影视女演员，出生于北京，毕业于北京舞蹈学院芭蕾舞专业2006届本科班。2004年因为出演《月影风荷》中的女主角叶风荷而踏入演艺圈。之后相继参演了《射雕英雄传》、《仙剑奇侠传三》等影视作品，开始受到观众关注。2011年，因饰演《步步惊心》中的马尔泰·若曦一角使她受到了广泛关注，其他影视作品有《不二神探》《绣春刀》《深夜前的五分钟》2013年11月13日凌晨，吴奇隆在微博上首先发布消息承认与刘诗诗的恋情，2015年1月20日，刘诗诗与吴奇隆登记结婚。",
            "毕业于北京电影学院2002级表演系本科班。2002年出演《金粉世家》和《天龙八部》受到关注；2004年主演神话剧《仙剑奇侠传》人气大增；2006年饰演《神雕侠侣》中的小龙女一角而受到更广泛关注，同年发行首张个人专辑《刘亦菲》。2008年与成龙、李连杰等出演好莱坞电影《功夫之王》。2011年主演的电影《新倩女幽魂》和《鸿门宴》以及2012年主演的电影《四大名捕》系列和《铜雀台》均票房过亿。2014年凭借《铜雀台》获得第五届澳门国际电影节最佳女主角奖；同年主演都市爱情电影《露水红颜》于11月7日上映。",
            "高圆圆，中国女演员，1979年10月5日出生于北京市丰台区云岗一个普通的知识分子家庭。1996年高圆圆被广告公司发掘，随后拍摄了大量广告，成为了广告圈中的模特。1997年高圆圆出演了她的第一部电影《爱情麻辣烫》，从此开始了她的演员生涯。2001年高圆圆参演的电影《十七岁的单车》获得柏林国际电影节最佳影片银熊奖。主要影视作品有《倚天屠龙记》《青红》《咱们结婚吧》《搜索》《一生一世》等。2014年6月5日，赵又廷和高圆圆在北京领证结婚；11月28日，赵又廷和高圆圆在台北举行婚礼。",
            "主持风格热辣、活泼、急智，着装性感。毕业于湖南师范大学汉语语言学院本科班。1999年参加广州“美花城”初赛并签约广州电视台34频道担任节目主持人，随后又参与拍摄了多条广告、小品剧集和短剧等作品。2005年9月参加《猫人超级魅力主持秀》电视节目获第七名。2009年获得第二届音乐风云榜新人盛典“年度最佳多栖新人”奖。2010年获得第三届“《综艺》年度节目暨电视人”年度最具潜力主持人”以及中国十大最具网络影响力的电视主持人。2013年参演动作喜剧电影《不二神探》；参演推理惊悚电影《人间蒸发》；12月6日，柳岩参演电影《四大名捕2》上映。2014年参加湖北卫视明星恋爱真人秀节目《如果爱》。",
            "孙俪，本名孙丽，1982年9月26日出生于上海市，中国大陆女演员、歌手。2001年参加新加坡举行的“才华横溢出新秀”比赛获奖，随后签约北京海润演艺经纪有限公司。2003年因饰演海岩剧《玉观音》女主角安心成名并荣获金鹰奖；2006年凭借《霍元甲》荣获第28届大众电影百花奖最佳新人奖。2012年孙俪产后复出，出演清宫剧《后宫·甄嬛传》，在剧中饰演甄嬛一角，并因此获第41届国际艾美奖最佳女主角提名。2013年在蛇年春晚和李健合唱《风吹麦浪》。2014年因出演时装剧《辣妈正传》荣获第20届上海电视节\"白玉兰奖\"最佳女演员奖和第27届金鹰奖最具人气女演员奖。",
            "从16岁起开始兼职做平面模特和舞蹈演员。曾就读于新疆艺术学院附中。2011年成功考入北京电影学院表演系本科班，之后签约上海唐人电影制作有限公司，在电视剧《轩辕剑之天之痕》中饰演于小雪一角被观众熟知。2012年参演了周杰伦《红尘客栈》MV的拍摄。2013年参演电影《警察故事2013》，2014年参演电影《分手大师》《痞子英雄之黎明升起》和《全城通缉》。",
            "1986年，3岁的李小璐客串出演《我只流三次泪》。1998年，17岁的李小璐凭借和陈冲合作的《天浴》一片获得金马奖影后和获得法国水城首届亚洲电影节最佳女演员奖。2002年2月，电视剧《少年张三丰》首播，李小璐在剧中饰演角色明道红。2004年2月，李小璐出演的《十三格格》和古装武侠连续剧《绝世双骄》播出。2012年7月6日，李小璐与贾乃亮举行婚礼；10月23日，李小璐在北京诞下女儿。2013年，李小璐加盟冯小刚执导的喜剧电影《私人定制》。"
    };

    Random ran = new Random();

    private int cardWidth;
    private int cardHeight;

    private SwipeCardView swipeView;
    private InnerAdapter adapter;

    private ProfileFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();
    }

    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (338 * density));


        swipeView = (SwipeCardView) findViewById(R.id.swipeCardView);
        //swipeView.setIsNeedSwipe(true);
        swipeView.setSwipeListener(this);
        swipeView.setOnItemClickListener(this);

        adapter = new InnerAdapter();
        swipeView.setAdapter(adapter);

        FragmentManager manager = getSupportFragmentManager();
        frag = new ProfileFragment();
        manager.beginTransaction().add(R.id.contentView, frag).commit();
    }

    @Override
    public void onItemClicked(MotionEvent event, View view, Object dataObject) {
        if (view.getTag() instanceof ViewHolder) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            ViewHolder vh = (ViewHolder) view.getTag();
            View child = vh.portraitView;
            Rect outRect = new Rect();
            child.getGlobalVisibleRect(outRect);
            if (outRect.contains(x, y)) {
                makeToast(MainActivity.this, "查看资料");

                Bundle bundle = new Bundle();
                bundle.putString("text", "个人资料");
                frag.show(view,bundle);
            } else {
                outRect.setEmpty();
                child = vh.collectView;
                child.getGlobalVisibleRect(outRect);
                if (outRect.contains(x, y)) {
                    makeToast(MainActivity.this, "关注明星");
                }
            }
        }
    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        makeToast(MainActivity.this, "向左滑动");
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        makeToast(MainActivity.this, "向右滑动");
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
    }

    private void loadData() {
        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, List<Star>>() {
            @Override
            protected List<Star> doInBackground(Void... params) {
                ArrayList<Star> list = new ArrayList<>(10);
                Star star;
                for (int i = 0; i < 10; i++) {
                    star = new Star();
                    star.portrait = portraits[i];
                    star.name = names[i];
                    star.city = citys[i];
                    star.sign = signs[i];
                    star.height = heights[i];
                    star.weight = weights[i];
                    list.add(star);
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<Star> list) {
                super.onPostExecute(list);
                adapter.addAll(list);
            }
        });
    }


    private class InnerAdapter extends BaseAdapter {

        ArrayList<Star> objs;

        public InnerAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(Collection<Star> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public Star getItem(int position) {
            if (objs == null || objs.size() == 0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Star star = getItem(position);
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
            ViewHolder holder = new ViewHolder();
            convertView.setTag(holder);
            convertView.getLayoutParams().width = cardWidth;
            holder.portraitView = (SimpleDraweeView) convertView.findViewById(R.id.portrait);

            //holder.portraitView.getLayoutParams().width = cardWidth;
            holder.portraitView.getLayoutParams().height = cardHeight;

            holder.nameView = (TextView) convertView.findViewById(R.id.name);
            holder.cityView = (TextView) convertView.findViewById(R.id.city);
            holder.signView = (TextView) convertView.findViewById(R.id.sign);
            holder.heightView = (TextView) convertView.findViewById(R.id.height);
            holder.weightView = (TextView) convertView.findViewById(R.id.weight);
            holder.collectView = (CheckedTextView) convertView.findViewById(R.id.collect);

            holder.portraitView.setImageURI(Uri.parse(star.portrait));
            holder.nameView.setText(String.format("%s", star.name));

            final CharSequence no = "暂无";

            holder.cityView.setHint(no);
            holder.cityView.setText(star.city);
            holder.cityView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.card_icon_city, 0, 0);

            holder.signView.setHint(no);
            holder.signView.setText(star.sign);
            holder.signView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.card_icon_sign, 0, 0);

            holder.heightView.setHint(no);
            holder.heightView.setText(star.height + "cm");
            holder.heightView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.card_icon_height, 0, 0);

            holder.weightView.setHint(no);
            holder.weightView.setText(star.weight + "kg");
            holder.weightView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.card_icon_weight, 0, 0);

            return convertView;
        }

    }

    private static class ViewHolder {
        SimpleDraweeView portraitView;
        TextView nameView;
        TextView cityView;
        TextView signView;
        TextView heightView;
        TextView weightView;
        CheckedTextView collectView;

    }

    public static class Star {

        public String portrait;
        public String name;
        public String city;
        public String sign;
        public String height;
        public String weight;
    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

}