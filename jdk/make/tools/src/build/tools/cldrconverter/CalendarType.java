/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package build.tools.cldrconverter;

import java.util.Locale;

/**
 * Constants for the Calendars supported by JRE.
 */
enum CalendarType {

    GREGORIAN, BUDDHIST, JAPANESE;

    private static final int[][] ERA_DATA = {
        // start index, array length
        {0,   2},   // gregorian
        {0,   1},   // buddhist
        {232, 4},   // japanese (eras from Meiji)
    };

    private final String lname; // lowercase name

    private CalendarType() {
        lname = name().toLowerCase(Locale.ROOT);
    }

    String lname() {
        return lname;
    }

    String keyElementName() {
        return (this == GREGORIAN) ? "" : lname + ".";
    }

    int normalizeEraIndex(int index) {
        index -= ERA_DATA[ordinal()][0];
        if (index >= ERA_DATA[ordinal()][1]) {
            index = -1;
        }
        return index;
    }

    int getEraLength(String name) {
        return ERA_DATA[ordinal()][1];
    }

    static CalendarType forName(String name) {
        for (CalendarType type : values()) {
            if (type.lname.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
