/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.bpodolski.caspergis.gui.geotools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapContent;
import org.geotools.referencing.operation.transform.AffineTransform2D;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;
import org.geotools.swing.MapLayerTable;
import org.geotools.swing.action.InfoAction;
import org.geotools.swing.action.NoToolAction;
import org.geotools.swing.action.PanAction;
import org.geotools.swing.action.ResetAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swing.action.ZoomOutAction;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.tool.CursorTool;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTWriter;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;
import org.opengis.referencing.operation.MathTransform;

/**
 *
 * @author bpodolski
 */
public class JMapPanelCG extends javax.swing.JPanel {

    private static final Color LINE_COLOUR = Color.BLUE;
    private static final Color FILL_COLOUR = Color.CYAN;
    private static final Color SELECTED_COLOUR = Color.YELLOW;

    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
    private String geometryAttributeName;

    /*
     * The following toolbar button names are primarily for unit testing
     * but could also be useful for applications wanting to control appearance
     * and behaviour at run-time.
     */
    /**
     * Name assigned to toolbar button for feature info queries.
     */
    public static final String TOOLBAR_INFO_BUTTON_NAME = "ToolbarInfoButton";
    /**
     * Name assigned to toolbar button for map panning.
     */
    public static final String TOOLBAR_PAN_BUTTON_NAME = "ToolbarPanButton";
    /**
     * Name assigned to toolbar button for default pointer.
     */
    public static final String TOOLBAR_POINTER_BUTTON_NAME = "ToolbarPointerButton";
    /**
     * Name assigned to toolbar button for map reset.
     */
    public static final String TOOLBAR_RESET_BUTTON_NAME = "ToolbarResetButton";
    /**
     * Name assigned to toolbar button for map zoom in.
     */
    public static final String TOOLBAR_ZOOMIN_BUTTON_NAME = "ToolbarZoomInButton";
    /**
     * Name assigned to toolbar button for map zoom out.
     */
    public static final String TOOLBAR_ZOOMOUT_BUTTON_NAME = "ToolbarZoomOutButton";

    /**
     * Constants for available toolbar buttons used with the {@link #enableTool}
     * method.
     */
    public enum Tool {
        /**
         * Simple mouse cursor, used to unselect previous cursor tool.
         */
        POINTER,
        /**
         * The feature info cursor tool
         */
        INFO,
        /**
         * The panning cursor tool.
         */
        PAN,
        /**
         * The reset map extent cursor tool.
         */
        RESET,
        /**
         * The zoom display cursor tools.
         */
        ZOOM,
        /**
         * The map should zoom with the mouse wheel. No button shown for this.
         */
        SCROLLWHEEL;
    }

    private boolean showToolBar;
    private Set<Tool> toolSet;

    /*
     * UI elements
     */
    private JMapPane mapPane;
    private MapLayerTable mapLayerTable;
    private JToolBar toolBar;
    private MapContent content = new MapContent();

    private boolean showStatusBar;
    private boolean showLayerTable;
    private boolean uiSet;

    public JMapPanelCG() {
        initComponents();

        showLayerTable = false;
        showStatusBar = false;
        showToolBar = false;
        toolSet = EnumSet.noneOf(Tool.class);

        // the map pane is the one element that is always displayed
        mapPane = new JMapPane(content);
        mapPane.setBackground(Color.WHITE);
        mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mapPane.addFocusListener(
                new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

            @Override
            public void focusLost(FocusEvent e) {
                mapPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });

        mapPane.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mapPane.requestFocusInWindow();
            }
        });

        initComponents2();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnlTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlTop.setMinimumSize(new java.awt.Dimension(33, 33));
        pnlTop.setPreferredSize(new java.awt.Dimension(33, 33));
        pnlTop.setLayout(new javax.swing.BoxLayout(pnlTop, javax.swing.BoxLayout.LINE_AXIS));
        add(pnlTop, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Sets whether to display the default toolbar (default is false). Calling
     * this with state == true is equivalent to calling {@link #enableTool} with
     * all {@link JMapFrame.Tool} constants.
     *
     * @param enabled whether the toolbar is required
     */
    public void enableToolBar(boolean enabled) {
        if (enabled) {
            toolSet = EnumSet.allOf(Tool.class);
        } else {
            toolSet.clear();
        }
        showToolBar = enabled;
    }

    /**
     * This method is an alternative to {@link #enableToolBar(boolean)}. It
     * requests that a tool bar be created with specific tools, identified by
     * {@link JMapFrame.Tool} constants. <code><pre>
     * myMapFrame.enableTool(Tool.PAN, Tool.ZOOM);
     * </pre></code> Calling this method with no arguments or {@code null} is
     * equivalent to {@code
     * enableToolBar(false)}.
     *
     * @param tool tools to display on the toolbar
     */
    public void enableTool(Tool... tool) {
        if (tool == null || tool.length == 0) {
            enableToolBar(false);
        } else {
            toolSet = EnumSet.copyOf(Arrays.asList(tool));
            showToolBar = true;
        }
    }

    /**
     * Set whether a status bar will be displayed to display cursor position and
     * map bounds.
     *
     * @param enabled whether the status bar is required.
     */
    public void enableStatusBar(boolean enabled) {
        showStatusBar = enabled;
    }

    /**
     * Set whether a map layer table will be displayed to show the list of
     * layers in the map content and set their order, visibility and selected
     * status.
     *
     * @param enabled whether the map layer table is required.
     */
    public void enableLayerTable(boolean enabled) {
        showLayerTable = enabled;
    }

    /**
     * Creates and lays out the frame's components that have been specified with
     * the enable methods (e.g. {@link #enableToolBar(boolean)} ). If not called
     * explicitly by the client this method will be invoked by {@link #setVisible(boolean)
     * } when the frame is first shown.
     */
    public void initComponents2() {
        mapLayerTable = new MapLayerTable(mapPane);
        Dimension minimumSize = new Dimension(140, 444);
        mapLayerTable.setMinimumSize(minimumSize);
        mapLayerTable.setMapPane(mapPane);

        this.add(this.mapPane, java.awt.BorderLayout.CENTER);
        this.add(this.mapLayerTable, java.awt.BorderLayout.WEST);

//        mapPane.addMouseListener(new ScrollWheelTool(mapPane));
        ButtonGroup cursorToolGrp = new ButtonGroup();
        JButton btn;
        btn = new JButton(new NoToolAction(mapPane));
        btn.setName(TOOLBAR_POINTER_BUTTON_NAME);
        this.pnlTop.add(btn);
        cursorToolGrp.add(btn);

        btn = new JButton(new ZoomInAction(mapPane));
        btn.setName(TOOLBAR_ZOOMIN_BUTTON_NAME);
        this.pnlTop.add(btn);
        cursorToolGrp.add(btn);

        btn = new JButton(new ZoomOutAction(mapPane));
        btn.setName(TOOLBAR_ZOOMOUT_BUTTON_NAME);
        this.pnlTop.add(btn);
        cursorToolGrp.add(btn);

        btn = new JButton(new PanAction(mapPane));
        btn.setName(TOOLBAR_PAN_BUTTON_NAME);
        this.pnlTop.add(btn);
        cursorToolGrp.add(btn);

        btn = new JButton(new InfoAction(mapPane));
        btn.setName(TOOLBAR_INFO_BUTTON_NAME);
        this.pnlTop.add(btn);

        btn = new JButton(new ResetAction(mapPane));
        btn.setName(TOOLBAR_RESET_BUTTON_NAME);
        this.pnlTop.add(btn);

        btn = new JButton("Select");
        this.pnlTop.add(btn);
        /*
         * When the user clicks the button we want to enable
         * our custom feature selection tool. Since the only
         * mouse action we are intersted in is 'clicked', and
         * we are not creating control icons or cursors here,
         * we can just create our tool as an anonymous sub-class
         * of CursorTool.
         */
        btn.addActionListener(e -> getMapPane().setCursorTool(
                new CursorTool() {
            @Override
            public void onMouseClicked(MapMouseEvent ev) {
                selectFeatures(ev);
            }
        }));

    }

    void selectFeatures(MapMouseEvent ev) {

        FeatureSource<?, ?> featureSource = this.content.layers().get(0).getFeatureSource();
        GeometryDescriptor geomDesc = featureSource.getSchema().getGeometryDescriptor();
        geometryAttributeName = geomDesc.getLocalName();

       /*
         * Construct a 5x5 pixel rectangle centred on the mouse click position
         */
        Point screenPos = ev.getPoint();
        Rectangle screenRect = new Rectangle(screenPos.x - 2, screenPos.y - 2, 5, 5);

        /*
         * Transform the screen rectangle into bounding box in the coordinate
         * reference system of our map context. Note: we are using a naive method
         * here but GeoTools also offers other, more accurate methods.
         */
        AffineTransform screenToWorld = getMapPane().getScreenToWorldTransform();
        Rectangle2D worldRect = screenToWorld.createTransformedShape(screenRect).getBounds2D();
        ReferencedEnvelope bbox = new ReferencedEnvelope(worldRect, getMapContent().getCoordinateReferenceSystem());

        /*
         * Create a Filter to select features that intersect with
         * the bounding box
         */
        Filter filter = ff.intersects(ff.property(geometryAttributeName), ff.literal(bbox));

        /*
         * Use the filter to identify the selected features
         */
        try {
            SimpleFeatureCollection selectedFeatures = (SimpleFeatureCollection) featureSource.getFeatures(filter);

            Set<FeatureId> IDs = new HashSet<>();
            try ( SimpleFeatureIterator iter = selectedFeatures.features()) {
                while (iter.hasNext()) {
                    SimpleFeature feature = iter.next();
                    IDs.add(feature.getIdentifier());
                    WKTWriter wktWriter = new WKTWriter();

                    Geometry geom = (Geometry) feature.getDefaultGeometry();

                    AffineTransform worldToScreen = getMapPane().getWorldToScreenTransform();
                    MathTransform transform = new AffineTransform2D(worldToScreen);
                    Geometry geometry2 = JTS.transform(geom, transform);

                    GeneralPath s = new GeneralPath();

//                    geom.getBoundary().getCoordinates().length
                    Coordinate[] pkts = geometry2.getCoordinates();
                    Coordinate pkt = pkts[0];
                    s.moveTo(pkt.x, pkt.y);
                    for (int k = 1; k < pkts.length; k++) {
                        pkt = pkts[k];
                        s.lineTo(pkt.x, pkt.y);
                    }
                    s.closePath();
                }
            }

            if (IDs.isEmpty()) {
                System.out.println("   no feature selected");
            }

//            displaySelectedFeatures(IDs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the map content associated with this frame. Returns {@code null} if
     * no map content has been set explicitly with the constructor or
     * {@link #setMapContent}.
     *
     * @return the current {@code MapContent} object
     */
    public MapContent getMapContent() {
        return mapPane.getMapContent();
    }

    /**
     * Set the MapContent object used by this frame.
     *
     * @param content the map content
     * @throws IllegalArgumentException if content is null
     */
    public void setMapContent(MapContent content) {
        if (content == null) {
            throw new IllegalArgumentException("map content must not be null");
        }

        mapPane.setMapContent(content);
    }

    /**
     * Provides access to the instance of {@code JMapPane} being used by this
     * frame.
     *
     * @return the {@code JMapPane} object
     */
    public JMapPane getMapPane() {
        return mapPane;
    }

    /**
     * Provides access to the toolbar being used by this frame. If
     * {@link #initComponents} has not been called yet this method will invoke
     * it.
     *
     * @return the toolbar or null if the toolbar was not enabled
     */
    public JToolBar getToolBar() {
        if (!uiSet) {
            initComponents();
        }
        return toolBar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables

}
