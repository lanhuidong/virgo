package com.nexusy.virgo.web.listener;

import ch.qos.logback.classic.LoggerContext;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <ol>
 * <li>清理MySQL JDBC线程</li>
 * <li>关闭Logback线程</li>
 * </ol>
 *
 * @author lanhuidong
 * @since 2017-01-09
 */
public class CleanupListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            LOGGER.info("Clean MySQL JDBC driver thread.");
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            LOGGER.info("MySQL JDBC driver clean up error", e);
        }
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.stop();
    }
}
