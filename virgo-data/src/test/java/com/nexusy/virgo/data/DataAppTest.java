package com.nexusy.virgo.data;

import com.nexusy.virgo.data.config.DataSourceConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lan
 * @since 2013-11-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@Transactional
public abstract class DataAppTest {

}
