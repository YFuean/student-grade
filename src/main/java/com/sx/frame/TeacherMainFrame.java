package com.sx.frame;

import com.sx.entity.*;
import com.sx.factory.DAOFactory;
import com.sx.factory.ServiceFactory;
import com.sx.service.TeacherService;
import com.sx.ui.ImgPanel;
import com.sx.utils.AliOSSUtil;
import com.eltima.components.ui.DatePicker;
import com.sx.utils.ImgThread;
import com.sx.utils.Password;
import net.coobird.thumbnailator.Thumbnails;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TeacherMainFrame extends JFrame{
    private Teacher teacher;
    private ImgPanel rootPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JLabel teacherAvatarLabel;
    private JLabel teacherNameLabel;
    private JLabel teacherIdLabel;
    private JPanel personPanel;
    private JPanel gradePanel;
    private JPanel countPanel;
    private JLabel personLabel;
    private JLabel gradeLabel;
    private JLabel countLabel;
    private JPasswordField teaPasField;
    private JLabel teaIdLabel;
    private JLabel teaNamLabel;
    private JLabel teaGenLabel;
    private JLabel teaDepLabel;
    private JLabel teaJobLabel;
    private JLabel teaEduLabel;
    private JLabel teaAvaLabel;
    private JButton 编辑Button;
    private JButton 保存Button;
    private JPanel addGradePanel;
    private JComboBox<String> departmentComboBox;
    private JComboBox<Student> NameComboBox;
    private JComboBox<Course> CourseComboBox;
    private JTextField gradeTextField;
    private JPanel testDatePanel;
    private JButton 添加Button;
    private JLabel studentIdLabel;
    private JPanel gradeTablePanel;
    private JLabel firstPageLabel;
    private JPanel firstPanel;
    private JLabel teacherLabel;
    private File file,toPic;
    private String uploadFileUrl;
    private String departmentName;
    private String studentId;
    private int courseId;
    private int row;

    public TeacherMainFrame(Teacher teacher) {
        this.teacher = teacher;
        teacherNameLabel.setText(teacher.getName());
        teacherIdLabel.setText(teacher.getId());
        teacherAvatarLabel.setText("<html><img src='"+ teacher.getAvatar() + "'></html>");
        setTitle("教师主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        rootPanel.setFileName("teacherBG.png");
        rootPanel.repaint();
        leftPanel.setOpaque(false);
        topPanel.setOpaque(false);
        centerPanel.setOpaque(false);

        DatePicker datePicker = getDatePicker();
        testDatePanel.add(datePicker);
        testDatePanel.revalidate();

        //获取centerPanel,获取的是LayoutManager,向下转型CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        cardLayout.show(centerPanel,"Card3");
        personLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card1");
                showPerson();
            }
        });
        gradeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card2");
                List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByTeacherId(teacher.getId());
                showGrade(studentGradeVOList);
                addGradeComboBox();
            }
        });
        firstPageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card3");
            }
        });
        countLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card4");
                List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByTeacherId(teacher.getId());
                showCount(studentGradeVOList);
            }
        });
        编辑Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teaPasField.setEditable(true);
            }
        });
        teaAvaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:/QLDownload"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    teaAvaLabel.removeAll();
                    //选中文件,原图
                    file = fileChooser.getSelectedFile();
                    //指定缩略图大小
                    toPic=fileChooser.getSelectedFile();
                    //此处把图片压成100×100的缩略图
                    try {
                        Thumbnails.of(file).size(100,100).toFile(toPic);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(toPic.getAbsolutePath());
                    //通过标签显示图片
                    teaAvaLabel.setIcon(icon);
                    //设置标签可见
                    teaAvaLabel.setVisible(true);
                }
            }
        });
        保存Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //获取密码框密码
                String p = new String(teaPasField.getPassword()).trim();
                //获取加密
                Password password = new Password();
                String mde5PassWord = password.encryption(p);
                TeacherService teacherService = ServiceFactory.getTeacherServiceInstance();
                teacher.setId(teaIdLabel.getText());
                teacher.setName(teaNamLabel.getText());
                teacher.setDepartment(teaDepLabel.getText());
                teacher.setGender(teaGenLabel.getText());
                teacher.setPassword(mde5PassWord);
                teacher.setJobTitle(teaJobLabel.getText());
                teacher.setEducation(teaEduLabel.getText());
                teacher.setAvatar(uploadFileUrl);
                int n = teacherService.updateTeacher(teacher);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "修改成功");
                    //将侧边栏隐藏
                    teaPasField.setEditable(false);
                    //刷新数据
                    teacherAvatarLabel.setText("<html><img src='"+uploadFileUrl+"'></html>");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "修改失败");
                }
            }
        });
        添加Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scoreStr = gradeTextField.getText();
                int scoreInt = Integer.valueOf(scoreStr);
                Grade grade = new Grade();
                grade.setStudentId(studentId);
                grade.setCourseId(courseId);
                grade.setTeacherId(teacher.getId());
                grade.setScore(scoreInt);
                grade.setTestDate((Date) datePicker.getValue());
                int n = ServiceFactory.getStuGraVOServiceInstance().insertGrade(grade);
                if (n == 1){
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                }
                List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByTeacherId(teacher.getId());
                showGrade(studentGradeVOList);
                studentIdLabel.setText("");
                gradeTextField.setText("");
            }
        });
        teacherLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(URI.create("http://jyt.jiangsu.gov.cn/"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    private void showGrade(List<StudentGradeVO> studentGradeVOList) {
        gradeTablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(new String[]{"id","测试日期","课程","学生学号","学生姓名","成绩"});
        for (StudentGradeVO studentGradeVO:studentGradeVOList) {
            Object[] object = new Object[]{studentGradeVO.getGradeId(),studentGradeVO.getTestDate().toString(),
                    studentGradeVO.getCourseName(),studentGradeVO.getStudentId(),
                    studentGradeVO.getStudentName(),studentGradeVO.getScore()};
            model.addRow(object);
        }
        //将第一列隐藏头像
        TableColumn tc = table.getColumnModel().getColumn(0);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        //获得表头
        JTableHeader header = table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        header.setFont(new Font("微软雅黑",Font.PLAIN,28));
        table.setRowHeight(35);
        table.setBackground(new Color(188, 228, 239));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        table.setFont(new Font("微软雅黑",Font.PLAIN,22));
        JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        gradeTablePanel.add(scrollPane);
        gradeTablePanel.setOpaque(false);

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        item.setFont(new Font("微软雅黑",Font.PLAIN,22));
        jPopupMenu.add(item);
        table.add(jPopupMenu);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                if (e.getButton() == 3){
                    jPopupMenu.show(table,e.getX(),e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString =  table.getValueAt(row,0).toString();
                int id = Integer.valueOf(idString).intValue();
                int choice = JOptionPane.showConfirmDialog(rootPanel,"确定要删除吗？");
                if (choice == 0){
                    if(row != -1){
                        model.removeRow(row);
                    }
                    ServiceFactory.getStuGraVOServiceInstance().deleteGrade(id);
                }
            }
        });
    }
    private void showPerson() {
        personPanel.setOpaque(false);
        teaIdLabel.setText(teacher.getId());
        teaNamLabel.setText(teacher.getName());
        teaGenLabel.setText(teacher.getGender());
        teaDepLabel.setText(teacher.getDepartment());
        teaJobLabel.setText(teacher.getJobTitle());
        teaEduLabel.setText(teacher.getEducation());
        teaPasField.setText(teacher.getPassword());
        teaAvaLabel.setText("<html><img src='"+ teacher.getAvatar() + "'></html>");
    }
    private void addGradeComboBox(){
        String[] depTip = {"请选择院系","计算机与软件学院","艺术设计学院","交通工程学院","机械工程学院",
                "电气工程学院","航空工程学院","商务贸易学院","经济管理学院","国际贸易学院"};
        for (int i= 0;i<depTip.length;i++){
            departmentComboBox.addItem(depTip[i]);
        }
        Student stuTip = new Student();
        stuTip.setName("请选择学生");
        NameComboBox.addItem(stuTip);
        List<Student> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
        for (Student student : studentList) {
            NameComboBox.addItem(student);
        }
        departmentComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED ){
                    int index = departmentComboBox.getSelectedIndex();
                    if (index !=0){
                        departmentName = departmentComboBox.getItemAt(index);
                        List<Student> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartment(departmentName);
                        NameComboBox.removeAllItems();
                        Student stuTip = new Student();
                        stuTip.setName("请选择学生");
                        NameComboBox.addItem(stuTip);
                        for (Student student : studentList) {
                            NameComboBox.addItem(student);
                        }
                    }
                }
            }
        });
        NameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = NameComboBox.getSelectedIndex();
                    if ( index != 0){
                        studentId = NameComboBox.getItemAt(index).getId();
                        studentIdLabel.setText(studentId);
                    }
                }
            }
        });
        Course tip = new Course();
        tip.setName("请选择课程");
        CourseComboBox.addItem(tip);
        List<Course> courseList = ServiceFactory.getCourseServiceInstance().selectAll();
        for (Course course:courseList) {
            CourseComboBox.addItem(course);
        }
        CourseComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = CourseComboBox.getSelectedIndex();
                if (index !=0){
                    courseId = CourseComboBox.getItemAt(index).getId();
                }
            }
        });
    }
    private static DatePicker getDatePicker() {
        final DatePicker datePicker;
        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 22);
        Dimension dimension = new Dimension(200, 30);
        int[] hiLightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datePicker = new DatePicker(date,DefaultFormat,font,dimension);
        // datePicker.setLocation(137, 83);//设置起始位置

        //也可用setBounds()直接设置大小与位置
        //datePicker.setBounds(137, 83, 177, 24);

        // 设置一个月份中需要高亮显示的日子
        datePicker.setHightlightdays(hiLightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datePicker.setDisableddays(disabledDays);
        // 设置国家
        datePicker.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        // datePicker.setTimePanelVisible(true);
        return datePicker;
    }
    private void showCount(List<StudentGradeVO> studentGradeVOList) {
        countPanel.removeAll();
        CategoryDataset dataset = getDataSet(studentGradeVOList);
        JFreeChart chart = ChartFactory.createBarChart3D(
                //图标标题
                "学生成绩",
                //目录轴的显示标签
                "学生姓名",
                //数值轴的显示标签
                "分数",
                //数据集
                dataset,
                // 图表方向：水平、垂直
                PlotOrientation.VERTICAL,
                // 是否显示图例(对于简单的柱状图必须是false)
                true,
                //是否生成工具
                false,
                //是否生成URL链接
                false
        );
        //从这里开始
        CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();//水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));//水平底部标
        domainAxis.setTickLabelFont(new Font("黑体",Font.BOLD,14));//垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("黑体",Font.BOLD,20));//设置标题字体

        ChartPanel panel = new ChartPanel(chart,true);
        countPanel.add(panel);
    }
    private static CategoryDataset getDataSet(List<StudentGradeVO> studentGradeVOList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (StudentGradeVO studentGradeVO:studentGradeVOList) {
            dataset.addValue(studentGradeVO.getScore(),studentGradeVO.getCourseName(),studentGradeVO.getStudentName());
        }
        return dataset;
    }
    //public static void main(String[] args) throws Exception{ new TeacherMainFrame(DAOFactory.getTeacherDAOInstance().getTeacherById("")); }
    public static void main(String[] args) throws Exception{
        new TeacherMainFrame(DAOFactory.getTeacherDAOInstance().getTeacherById("1412666001"));
    }
}
