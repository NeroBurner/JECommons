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
import org.jevis.api.JEVisSample;
import org.jevis.commons.dataprocessing.DataProcessor;
import org.jevis.commons.dataprocessing.Task;

/**
 *
 * @author Florian Simon <florian.simon@envidatec.com>
 */
public class LimitCheckerProcessor implements DataProcessor {

    public static final String NAME = "Limit Checker";

    @Override
    public void resetResult() {
    }

    @Override
    public List<JEVisSample> getResult(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return NAME;
    }

}
