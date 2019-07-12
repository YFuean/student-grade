package com.sx.frame;

import com.sx.entity.Student;
import com.sx.entity.StudentGradeVO;
import com.sx.factory.DAOFactory;
import com.sx.factory.ServiceFactory;
import com.sx.service.StudentService;
import com.sx.ui.ImgPanel;
import com.sx.utils.AliOSSUtil;
import com.sx.utils.Password;
import net.coobird.thumbnailator.Thumbnails;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class StudentMainFrame extends JFrame{
    private Student student;
    private ImgPanel rootPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightTopPanel;
    private JPanel rightBottomPanel;
    private JLabel studentNameLabel;
    private JLabel studentIdLabel;
    private JLabel studentAvatarLabel;
    private JTextField stPassField;
    private JTextField stAdressField;
    private JLabel stIdLabel;
    private JLabel stNameLabel;
    private JLabel stGenderLabel;
    private JLabel stDepLabel;
    private JLabel stAdmLabel;
    private JLabel stBirLabel;
    private JLabel stAvatLabel;
    private JButton 保存Button;
    private JLabel personLabel;
    private JLabel gradeLabel;
    private JLabel teaCourseLabel;
    private JLabel 姓名,性别,学号,院系,生日,入学时间,地址,密码,头像;
    private JPanel gradePanel;
    private JPanel teacherCoursePanel;
    private JButton 打印Button;
    private JPanel gradeTablePanel;
    private JPanel firstPanel;
    private JLabel firstPageLabel;
    private JLabel learnLabel;
    private File file,toPic;
    private String uploadFileUrl;
    private JTable gradeTable;

    public StudentMainFrame(Student student) {
        this.student = student;
        studentNameLabel.setText(student.getName());
        studentIdLabel.setText(student.getId());
        studentAvatarLabel.setText("<html><img src='"+ student.getAvatar()+ "'></html>");
        setTitle("学生主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        rootPanel.setFileName("studentGB.png");
        rootPanel.repaint();
        leftPanel.setOpaque(false);
        rightTopPanel.setOpaque(false);
        rightBottomPanel.setOpaque(false);
        centerPanel.setOpaque(false);

        stIdLabel.setText(student.getId());
        stNameLabel.setText(student.getName());
        stDepLabel.setText(student.getDepartment());
        stGenderLabel.setText(student.getGender());
        stAdmLabel.setText(student.getAdmissionTime().toString());
        stBirLabel.setText(student.getBirthday().toString());
        stPassField.setText(student.getPassword());
        stAdressField.setText(student.getAddress());
        stAvatLabel.setText("<html><img src='"+ student.getAvatar()+ "'></html>");

        //获取centerPanel,获取的是LayoutManager,向下转型CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        cardLayout.show(centerPanel,"Card3");
        firstPageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card3");
            }
        });
        gradeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card1");
                gradePanel.repaint();
                showGrade();
            }
        });
        teaCourseLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(centerPanel,"Card2");
                showTeacherCourse();
            }
        });
        personLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                stIdLabel.setVisible(true);stNameLabel.setVisible(true);stGenderLabel.setVisible(true);
                stAdmLabel.setVisible(true);stBirLabel.setVisible(true);stPassField.setVisible(true);
                stAdressField.setVisible(true);stAvatLabel.setVisible(true);stDepLabel.setVisible(true);
                头像.setVisible(true);姓名.setVisible(true);性别.setVisible(true);学号.setVisible(true);
                院系.setVisible(true);生日.setVisible(true);入学时间.setVisible(true);地址.setVisible(true);密码.setVisible(true);
                保存Button.setVisible(true);
            }
        });
        stAvatLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:/QLDownload"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    stAvatLabel.removeAll();
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
                    stAvatLabel.setIcon(icon);
                    //设置标签可见
                    stAvatLabel.setVisible(true);
                }
            }
        });
        保存Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //获取加密密码
                Password password = new Password();
                String mde5PassWord = password.encryption(stPassField.getText());
                StudentService studentService = ServiceFactory.getStudentServiceInstance();
                student.setId(stIdLabel.getText());
                student.setName(stNameLabel.getText());
                student.setDepartment(stDepLabel.getText());
                student.setGender(stGenderLabel.getText());
                student.setPassword(mde5PassWord);
                student.setAddress(stAdressField.getText());
                student.setBirthday(student.getBirthday());
                student.setAdmissionTime(student.getAdmissionTime());
                student.setAvatar(uploadFileUrl);
                int n = studentService.updateStudent(student);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "修改成功");
                    //将侧边栏隐藏
                    stIdLabel.setVisible(false);stNameLabel.setVisible(false);stGenderLabel.setVisible(false);
                    stAdmLabel.setVisible(false);stBirLabel.setVisible(false);stPassField.setVisible(false);
                    stAdressField.setVisible(false);stAvatLabel.setVisible(false);stDepLabel.setVisible(false);
                    头像.setVisible(false);姓名.setVisible(false);性别.setVisible(false);学号.setVisible(false);
                    院系.setVisible(false);生日.setVisible(false);入学时间.setVisible(false);地址.setVisible(false);密码.setVisible(false);
                    保存Button.setVisible(false);
                    //刷新数据
                    studentAvatarLabel.setText("<html><img src='"+uploadFileUrl+"'></html>");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "修改失败");
                }
            }
        });
        打印Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("D:/QLDownload/grade.xlsx");
                if (!file.exists()){
                    try {
                        exportTable(gradeTable,file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        learnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(URI.create("http://www.jiangsugqt.org/"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    private void showTeacherCourse() {
        List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByStudentId(student.getId());
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(new String[]{"课程","教师","学分"});
        for (StudentGradeVO studentGradeVO:studentGradeVOList) {
            Object[] object = new Object[]{studentGradeVO.getCourseName(),
                    studentGradeVO.getTeacherName(),studentGradeVO.getCredit()};
            model.addRow(object);
        }
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
        teacherCoursePanel.add(scrollPane);
        teacherCoursePanel.setOpaque(false);
    }
    private void showGrade() {
        gradeTablePanel.removeAll();
        List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByStudentId(student.getId());
        gradeTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        gradeTable.setModel(model);
        model.setColumnIdentifiers(new String[]{"测试日期","课程","成绩"});
        for (StudentGradeVO studentGradeVO:studentGradeVOList) {
            Object[] object = new Object[]{studentGradeVO.getTestDate().toString(),
                    studentGradeVO.getCourseName(),studentGradeVO.getScore()};
            model.addRow(object);
        }
        //获得表头
        JTableHeader header = gradeTable.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        header.setFont(new Font("微软雅黑",Font.PLAIN,28));
        gradeTable.setRowHeight(35);
        gradeTable.setBackground(new Color(188, 228, 239));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        gradeTable.setDefaultRenderer(Object.class,r);
        gradeTable.setFont(new Font("微软雅黑",Font.PLAIN,22));
        JScrollPane scrollPane = new JScrollPane(gradeTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        gradeTablePanel.add(scrollPane);
        gradeTablePanel.setOpaque(false);
    }
    private void exportTable(JTable table,File file) throws IOException{
        TableModel model = table.getModel();//得到table的Model
        FileWriter out = new FileWriter(file);

        for (int i = 0; i < model.getColumnCount();i++){
            out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");
        for (int i = 0;i<model.getRowCount();i++){
            for (int j = 0;j<model.getColumnCount();j++){
                out.write(model.getValueAt(i,j).toString() + "\t");
            }
            out.write("\n");
        }
        out.close();
        System.out.println("write out to:" + file);
        JOptionPane.showMessageDialog(rootPanel,"导出成功");
    }
    //public static void main(String[] args) throws Exception{ new StudentMainFrame(DAOFactory.getStudentDAOInstance().getStudentById(""));}
    public static void main(String[] args) throws Exception{
        new StudentMainFrame(DAOFactory.getStudentDAOInstance().getStudentById("1802341101"));}
}
