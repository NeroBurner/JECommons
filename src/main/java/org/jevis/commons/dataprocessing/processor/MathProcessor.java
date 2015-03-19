/**
 * Copyright (C) 2015 Envidatec GmbH <info@envidatec.com>
 *
 * This file is part of JECommons.
 *
 * JECommons is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation in version 3.
 *
 * JECommons is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * JECommons. If not, see <http://www.gnu.org/licenses/>.
 *
 * JECommons is part of the OpenJEVis project, further project information are
 * published at <http://www.OpenJEVis.org/>.
 */
package org.jevis.commons.dataprocessing.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jevis.api.JEVisException;
import org.jevis.api.JEVisSample;
import org.jevis.commons.dataprocessing.DataProcessor;
import org.jevis.commons.dataprocessing.Options;
import org.jevis.commons.dataprocessing.Task;
import org.jevis.commons.dataprocessing.VirtuelSample;
import static org.jevis.commons.dataprocessing.Options.getAllTimestamps;
import org.jevis.commons.dataprocessing.VirtualAttribute;
import org.joda.time.DateTime;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class MathProcessor implements DataProcessor {

    public static final String NAME = "Math Processor";

    @Override
    public void resetResult() {
    }

    @Override
    public List<JEVisSample> getResult(Task mainTask) {
        List<JEVisSample> result = new ArrayList<>();

        List<Map<DateTime, JEVisSample>> sampleMaps = new ArrayList<>();
        List<List<JEVisSample>> allSamples = new ArrayList<>();
        for (Task task : mainTask.getSubTasks()) {

            try {
                allSamples.add(task.getResult());
                sampleMaps.add(Options.sampleListToMap(task.getResult()));
            } catch (JEVisException ex) {
                Logger.getLogger(MathProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<DateTime> allTimestamps = getAllTimestamps(allSamples);
        for (DateTime ts : allTimestamps) {
            double sum = 0d;
            for (Map<DateTime, JEVisSample> map : sampleMaps) {
                if (map.containsKey(ts)) {
                    try {
                        sum += map.get(ts).getValueAsDouble();
                    } catch (JEVisException ex) {
                        Logger.getLogger(MathProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            result.add(new VirtuelSample(ts, sum, mainTask.getJEVisDataSource(), new VirtualAttribute(null)));
        }

        return result;

    }

    @Override
    public String getName() {
        return NAME;
    }

}
