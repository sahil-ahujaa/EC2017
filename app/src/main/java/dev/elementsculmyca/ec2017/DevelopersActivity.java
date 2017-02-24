package dev.elementsculmyca.ec2017;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.elementsculmyca.ec2017.Adapters.DevelopersCardAdapter;
import dev.elementsculmyca.ec2017.Adapters.DevelopersCardItem;
import dev.elementsculmyca.ec2017.Adapters.DevelopersCardPagerAdapter;
import dev.elementsculmyca.ec2017.Adapters.ShadowTransformer;

public class DevelopersActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private DevelopersCardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        setTitle("Developers");

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new DevelopersCardPagerAdapter();
        mCardAdapter.addCardItem(new DevelopersCardItem(R.string.HemantName,R.string.HemantRole, R.drawable.hemant_pic,"https://www.linkedin.com/in/hemantbansal950",DevelopersActivity.this));
        mCardAdapter.addCardItem(new DevelopersCardItem(R.string.NamanName,R.string.NamanRole, R.drawable.ic_date1,"https://in.linkedin.com/in/namansachdeva",DevelopersActivity.this));
        mCardAdapter.addCardItem(new DevelopersCardItem(R.string.GauravName,R.string.GauravRole,R.drawable.ic_location1,"https://in.linkedin.com/in/gaurav-yadav-6248b5129",DevelopersActivity.this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        mCardShadowTransformer.enableScaling(true);
    }
}
