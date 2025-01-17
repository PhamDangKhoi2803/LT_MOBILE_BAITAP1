package vn.iotstar.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các layout
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        LinearLayout evenOddLayout = findViewById(R.id.evenOddLayout);

        // Nút chuyển đổi
        Button btnEvenOdd = findViewById(R.id.btnEvenOdd);
        Button btnProcessArray = findViewById(R.id.btnProcessArray);
        Button btnBack = findViewById(R.id.btnBack);

        // Các view trong layout số chẵn lẻ
        EditText edtInputArray = findViewById(R.id.edtInputArray);
        TextView tvResult = findViewById(R.id.tvResult);

        // Xử lý khi nhấn nút "Số Chẵn Lẻ"
        btnEvenOdd.setOnClickListener(v -> {
            mainLayout.setVisibility(View.GONE);
            evenOddLayout.setVisibility(View.VISIBLE);
        });

        // Xử lý khi nhấn nút "Phân loại số chẵn và số lẻ"
        btnProcessArray.setOnClickListener(v -> {
            String input = edtInputArray.getText().toString().trim();

            if (input.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập mảng số!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Chuyển chuỗi thành mảng số nguyên
                String[] stringNumbers = input.split(",");
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String num : stringNumbers) {
                    numbers.add(Integer.parseInt(num.trim()));
                }

                // Phân loại số chẵn và số lẻ
                ArrayList<Integer> evenNumbers = new ArrayList<>();
                ArrayList<Integer> oddNumbers = new ArrayList<>();

                for (int num : numbers) {
                    if (num % 2 == 0) {
                        evenNumbers.add(num);
                    } else {
                        oddNumbers.add(num);
                    }
                }

                // Hiển thị kết quả
                String result = "Số chẵn: " + evenNumbers + "\nSố lẻ: " + oddNumbers;
                tvResult.setText(result);

                // In ra Logcat
                Log.d("EvenNumbers", evenNumbers.toString());
                Log.d("OddNumbers", oddNumbers.toString());

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Định dạng không hợp lệ! Vui lòng nhập số cách nhau bởi dấu phẩy.", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý khi nhấn nút "Quay lại"
        btnBack.setOnClickListener(v -> {
            evenOddLayout.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);
        });
    }
}